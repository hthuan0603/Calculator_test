package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    var txtInput: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_cancel.setOnClickListener{
            input.text = ""
            output.text = ""
        }

        button_delete.setOnClickListener {
            var inp = txtInput?.text
            txtInput?.text = inp?.subSequence(0, inp.length-1)
        }

        button_equal.setOnClickListener {
            showResult()
        }

        button_left_Bracket.setOnClickListener {
            input.text = addToInputText("(")
        }
        button_right_Bracket.setOnClickListener {
            input.text = addToInputText(")")
        }
        button_chia.setOnClickListener {
            input.text = addToInputText("/")
        }
        button_nhan.setOnClickListener {
            input.text = addToInputText("*")
        }
        button_dot.setOnClickListener {
            input.text = addToInputText(".")
        }

        txtInput= findViewById(R.id.input)
    }

    private fun showResult() {
        try {
            val inp = ExpressionBuilder(input.text.toString()).build()
            val out = inp.evaluate()
            val longOutput = out.toLong()
            if (out == longOutput.toDouble()){
                output.text = longOutput.toString()
            }else{
                output.text = out.toString()
            }

        }catch (e:Exception){
            Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
        }
    }

    private fun addToInputText(buttonValue: String): String{
        return "${input.text}$buttonValue"
    }

    fun onDigital(view: View){
        var digital = (view as Button).text
        txtInput?.append(digital)
    }

    fun onCalculator(view: View){
        var c = (view as Button).text
        input.append(output.text)
        input.append(c)
        output.text = ""
    }
}








