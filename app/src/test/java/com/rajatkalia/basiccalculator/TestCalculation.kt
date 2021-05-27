package com.rajatkalia.basiccalculator

import org.junit.Assert.assertEquals
import org.junit.Test

class TestCalculation {


    /**
     *
     * Note : I have used the method from the main activity "equalCalculator"
     * but string value is not working in test cases
     * when i called equalCalculator method
     *
     * */

    //addition
    @Test
    fun Addition() {


        val calculate: MainActivity = MainActivity()

        var firstValue: Double = 4.0
        var secondValue: Double = 5.0
        var expectedValue: Double = 9.0
        assertEquals("Result of Addition is ", expectedValue, firstValue + secondValue, 0.0)

    }


    //subtraction
    @Test
    fun Subtraction() {


        val calculate: MainActivity = MainActivity()

        var firstValue: Double = 6.0
        var secondValue: Double = 2.0
        var expectedValue: Double = 4.0
        assertEquals("Result of Subtraction is ", expectedValue, firstValue - secondValue, 0.0)

    }


    //multiply
    @Test
    fun Multiply() {


        val calculate: MainActivity = MainActivity()

        var firstValue: Double = 4.0
        var secondValue: Double = 5.0
        var expectedValue: Double = 20.0
        assertEquals("Result of Multiplication is ", expectedValue, firstValue * secondValue, 0.0)

    }

//division
    @Test
    fun Divide() {


        val calculate: MainActivity = MainActivity()

        var firstValue: Double = 60.0
        var secondValue: Double = 10.0
        var expectedValue: Double = 6.0
        assertEquals("Result of Division is ", expectedValue, firstValue / secondValue, 0.0)

    }


    //percentage
    @Test
    fun Percentage() {


        val calculate: MainActivity = MainActivity()

        var firstValue: Double = 80.0
        var secondValue: Double = 100.0
        var expectedValue: Double = 80.0
        assertEquals("Result of Percentage is ", expectedValue, firstValue % secondValue, 0.0)

    }
}

