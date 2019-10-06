package sashjakk.calculator

import sashjakk.calculator.annotations.CalculatorOperation

@CalculatorOperation("+")
class AddOperation : Operation {
    override fun perform(a: Float, b: Float): Float = a + b
}

@CalculatorOperation("-")
class SubtractOperation : Operation {
    override fun perform(a: Float, b: Float): Float = a - b
}

@CalculatorOperation("*")
class MultiplyOperation : Operation {
    override fun perform(a: Float, b: Float): Float = a * b
}

@CalculatorOperation("/")
class DivideOperation : Operation {
    override fun perform(a: Float, b: Float): Float = a / b
}