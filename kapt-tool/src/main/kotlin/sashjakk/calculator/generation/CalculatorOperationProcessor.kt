package sashjakk.calculator.generation

import com.google.auto.service.AutoService
import sashjakk.calculator.annotations.CalculatorOperation
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedOptions
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
@SupportedOptions(CalculatorOperationProcessor.KOTLIN_GEN_DIRECTORY)
class CalculatorOperationProcessor : AbstractProcessor() {

    companion object {
        /** Kotlin generated code target directory option name. */
        const val KOTLIN_GEN_DIRECTORY = "kapt.kotlin.generated"
    }

    /** Kotlin generated code target directory. */
    private val targetDirectory: String
        get() = processingEnv.options[KOTLIN_GEN_DIRECTORY]
            ?: throw IllegalStateException("Unable to get target directory")

    /** Provides supported version of Java. */
    override fun getSupportedSourceVersion(): SourceVersion =
        SourceVersion.latestSupported()

    /** Provides set of annotations [CalculatorOperationProcessor] can make use of. */
    override fun getSupportedAnnotationTypes() =
        mutableSetOf(CalculatorOperation::class.java.canonicalName)

    override fun process(
        annotations: MutableSet<out TypeElement>,
        roundEnv: RoundEnvironment
    ): Boolean {
        if (annotations.isEmpty()) {
            return false
        }

        // NOTE: generate only once, subsequent runs provide empty set
        if (roundEnv.processingOver()) {
            return true
        }

        val operations = roundEnv
            .getElementsAnnotatedWith(CalculatorOperation::class.java)
            .filter { it.kind.isClass }
            .associate { element ->
                val constructor = element.enclosedElements.first {
                    it.kind == ElementKind.CONSTRUCTOR
                }
                val pkg = processingEnv.elementUtils.getPackageOf(constructor)
                val annotation = element.getAnnotation(CalculatorOperation::class.java)

                annotation.key to "$pkg.${element.simpleName}"
            }

        generateCalculator(operations)
            .writeTo(File(targetDirectory))

        return true
    }
}