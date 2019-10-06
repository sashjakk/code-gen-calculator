package sashjakk.calculator

import sashjakk.calculator.generated.GeneratedCalculator

fun main() {
    val a = 15f
    val b = 5f

    val calculator = Calculator()
    val generatedCalculator = GeneratedCalculator()

    listOf("+", "-", "*", "/").forEach { operation ->
        val result = calculator.calculate(operation, a, b)
        val genResult = generatedCalculator.calculate(operation, a, b)

        println("By hand: $a $operation $b = $result")
        println("Generated: $a $operation $b = $genResult\n")
    }
}