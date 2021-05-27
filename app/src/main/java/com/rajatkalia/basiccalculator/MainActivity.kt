package com.rajatkalia.basiccalculator

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    var str_input: String = "0"
    var str_result: String = "0"

    lateinit var et_input: EditText
    lateinit var tv_result: TextView

    var insertDecimal: Boolean = false
    var insertOperation: Boolean = false
    var inserplusMinus: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_input = findViewById(R.id.et_input)
        tv_result = findViewById(R.id.tv_result)


    }


    fun clickNumber(view: View) {

        var number = view as Button

        when (number.id) {

            btn_0.id -> {

                str_input += "0"

            }

            btn_1.id -> {

                str_input += "1"

            }

            btn_2.id -> {

                str_input += "2"

            }

            btn_3.id -> {


                str_input += "3"

            }

            btn_4.id -> {


                str_input += "4"

            }

            btn_5.id -> {

                str_input += "5"

            }

            btn_6.id -> {

                str_input += "6"
            }

            btn_7.id -> {

                str_input += "7"

            }

            btn_8.id -> {

                str_input += "8"

            }

            btn_9.id -> {

                str_input += "9"

            }


        }
        insertOperation = false

        showInput()


    }


    fun performOperation(view: View) {
        var calculatBtn = view as Button
        insertDecimal = false
        if (str_input.isNotEmpty()) {
            //check if the last digit is dot =>
            if (str_input.substring(
                    str_input.length - 1, str_input.length
                ).equals(".")
            ) {
                backSpace(view)
            }


            if (!insertOperation) {
                when (calculatBtn.id) {

                    btn_plus.id -> {
                        str_input += " + "
                    }

                    btn_minus.id -> {
                        str_input += " - "
                    }
                    btn_multiply.id -> {
                        str_input += " * "
                    }
                    btn_divide.id -> {
                        str_input += " / "
                    }

                    btn_percentage.id -> {
                        str_input += " % "

                    }


                }
            }


            var lastChar = (str_input.substring(
                str_input.length - 2,
                str_input.length
            )).trim()
            insertOperation =
                lastChar.equals("+") || lastChar.equals("-") || lastChar.equals("*") || lastChar.equals(
                    "/"
                ) || lastChar.equals("%")


            showInput()
        } else {

            Toast.makeText(this, "Enter values to perform operation", Toast.LENGTH_SHORT).show()
        }


    }


    //show input
    fun showInput() {
        et_input.setText(str_input)
    }

    //show output
    fun showOutput() {
        tv_result.setText(" Result :  $str_result")
    }

    fun clearAll(view: View) {

        insertDecimal = false
        insertOperation = false
        str_input = "0"
        str_result = "0"
        showInput()
        showOutput()
    }

    fun backSpace(view: View) {

        if (!TextUtils.isEmpty(str_input)) {

            if (str_input.substring(
                    str_input.length - 1,
                    str_input.length
                ).equals(".")
            ) {
                insertDecimal = false
            }


            if (str_input.substring(
                    str_input.length - 1,
                    str_input.length
                ) == " "
            ) {
                str_input = str_input.substring(0, str_input.length - 3)
                insertOperation = false
            } else {
                str_input = str_input.substring(0, str_input.length - 1)
            }


        } else {
            str_input = "0"
            str_result = "0"
            Toast.makeText(this, "enter value", Toast.LENGTH_SHORT).show()
        }
        showInput()
        showOutput()


    }

    //decimal operation
    fun clickDecimal(view: View) {
        if (TextUtils.isEmpty(str_input)) {
            str_input = "0."
            insertDecimal = true
        }
        if (insertDecimal == false) {

            str_input = "$str_input."
            insertDecimal = true
        }

        showInput()

    }

    fun equalCalculator(view: View) {

        try {

            /**
             *
             *For multiple operation calculations i use the library named as ExpressionBuilder.
             * */

            val expression = ExpressionBuilder(str_input).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result == longResult.toDouble())
                str_result = longResult.toString()
            else
                str_result = result.toString()

        } catch (e: Exception) {
            Log.d("Exception", " message : " + e.message)
            str_result = "Error";
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }


        showOutput()

    }

    //plusminus on last number
    fun plusMinusOperation(view: View) {

        if (str_input.isNotEmpty()) {

            if (str_input.length > 0) {
                var lastChar = (str_input.substring(
                    str_input.length - 1,
                    str_input.length
                )).trim()
                if (lastChar == "0" || lastChar == "1" || lastChar == "2" || lastChar == "3" || lastChar == "4" || lastChar == "5" || lastChar == "6" || lastChar == "7" || lastChar == "8" || lastChar == "9") {
                    var i = str_input.length - 1
                    str_input = addToString(str_input, i, "(-$lastChar)")
                    str_input = removeLastChar(str_input)
                    inserplusMinus = true
                } else if (lastChar.equals(")")) {
                    var lastnum = (str_input.substring(str_input.length - 2, str_input.length - 1))

                    str_input = replaceBetween(str_input, "(", ")", true, true, lastnum)
                } else {
                    Toast.makeText(this, "Last char should be number", Toast.LENGTH_SHORT).show()
                }


            } else {

                str_input = "-$str_input"
            }

        } else {

            str_input = "-"
        }

        showInput()


    }
    
    
    
    
    //brackets function
    fun clickBrackets(view: View) {
        Toast.makeText(this, "This button is not working", Toast.LENGTH_SHORT).show()
    }


    fun addToString(str: String, pos: Int, ins: String): String {
        return str.substring(0, pos) + ins + str.substring(pos)
    }

    fun removeLastChar(s: String?): String {
        return if (s == null || s.length == 0) null.toString() else s.substring(0, s.length - 1)
    }


    fun replaceBetween(
        str: String,
        start: String, end: String,
        startInclusive: Boolean,
        endInclusive: Boolean,
        replaceWith: String
    ): String {
        var str = str
        var i = str.indexOf(start)
        while (i != -1) {
            val j = str.indexOf(end, i + 1)
            if (j != -1) {
                var data = (if (startInclusive) str.substring(0, i) else str.substring(
                    0,
                    i + start.length
                )) +
                        replaceWith
                val temp = if (endInclusive) str.substring(j + end.length) else str.substring(j)
                data += temp
                str = data
                i = str.indexOf(start, i + replaceWith.length + end.length + 1)
            } else {
                break
            }
        }
        return str
    }

   

}