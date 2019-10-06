package sashjakk.calculator

class Calculator {
    private val operations = mapOf(
        "+" to AddOperation(),
        "-" to SubtractOperation(),
        "*" to MultiplyOperation(),
        "/" to DivideOperation()
    )

    fun calculate(operationKey: String, a: Float, b: Float): Float {
        val operation = operations[operationKey]
            ?: throw IllegalArgumentException("Unknown operation provided")

        return operation.perform(a, b)
    }
}