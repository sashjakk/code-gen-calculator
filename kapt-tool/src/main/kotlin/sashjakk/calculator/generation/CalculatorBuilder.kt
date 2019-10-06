package sashjakk.calculator.generation

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

internal fun generateCalculator(operations: Map<String, String>): FileSpec {
    val genFileName = "GeneratedCalculator"

    val operationsProp = createOperationsProperty(operations)
    val calculateFun = createCalculateFun()

    val calculator = TypeSpec.classBuilder(genFileName)
        .addFunction(calculateFun)
        .addProperty(operationsProp)
        .build()

    return FileSpec
        .builder("sashjakk.calculator.generated", genFileName)
        .addType(calculator)
        .build()
}

internal fun createOperationsProperty(
    operations: Map<String, String>
): PropertySpec {
    val code = CodeBlock.builder()
        .addStatement("mapOf(")
        .indent()

    var count = 0
    operations.forEach { (key, operation) ->
        var line = "%S to %T()"
        if (++count < operations.size) {
            line += ","
        }
        code.addStatement(line, key, toClassName(operation))
    }

    code.unindent()
        .addStatement(")")

    val stringClass = String::class.asClassName()
    val operationInterface = ClassName("sashjakk.calculator", "Operation")
    val mapClass = Map::class.asClassName()
        .parameterizedBy(stringClass, operationInterface)

    return PropertySpec
        .builder("operations", mapClass, KModifier.PRIVATE)
        .initializer(code.build())
        .build()
}

internal fun createCalculateFun(): FunSpec {
    val exceptionClass = IllegalArgumentException::class.asClassName()
    val errorMessage = "Unknown operation provided"

    val code = CodeBlock.builder()
        .add("val operation = operations[operationKey]\n")
        .indent()
        .add("?: throw %T(%S)\n\n", exceptionClass, errorMessage)
        .unindent()
        .add("return operation.perform(a, b)\n")
        .build()

    return FunSpec.builder("calculate")
        .addParameter("operationKey", String::class)
        .addParameter("a", Float::class)
        .addParameter("b", Float::class)
        .addCode(code)
        .returns(Float::class)
        .build()
}

internal fun toClassName(fullName: String): ClassName {
    val index = fullName.lastIndexOf('.')
    return ClassName(
        fullName.substring(0, index),
        fullName.substring(index + 1, fullName.length)
    )
}