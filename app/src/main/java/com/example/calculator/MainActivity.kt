package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastnumeric: Boolean = false
    var lastdot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvinput)
    }

    fun ondigit(view: View)
    {
        tvInput?.append((view as Button).text)
        lastnumeric=true
        lastdot=false
    }

    fun onclear(view: View)
    {
        tvInput?.text =""
    }

    fun ondecimal(view : View)
    {
        if(lastnumeric && !lastdot)
        {
            tvInput?.append(".")
            lastnumeric =false
            lastdot =true
        }
    }

    fun onequal(view : View){
        if(lastnumeric){
            var tvvalue = tvInput?.text.toString()
            var prefix=""
            try{
                if(tvvalue.startsWith("-")){
                    tvvalue=tvvalue.substring(1)
                }
                if(tvvalue.contains("-")){
                    val splitvalue = tvvalue.split("-")
                    var one = splitvalue[0]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var two = splitvalue[1]
                    tvInput?.text = removezeroafterresult((one.toDouble()-two.toDouble()).toString())
                }else if(tvvalue.contains("+")){
                    val splitvalue = tvvalue.split("+")
                    var one = splitvalue[0]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var two = splitvalue[1]
                    tvInput?.text = removezeroafterresult((one.toDouble()+two.toDouble()).toString())
                }else if(tvvalue.contains("*")){
                    val splitvalue = tvvalue.split("*")
                    var one = splitvalue[0]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var two = splitvalue[1]
                    tvInput?.text = removezeroafterresult((one.toDouble()*two.toDouble()).toString())
                }else if(tvvalue.contains("/")){
                    val splitvalue = tvvalue.split("/")
                    var one = splitvalue[0]
                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    var two = splitvalue[1]
                    tvInput?.text = removezeroafterresult((one.toDouble()/two.toDouble()).toString())
                }
            }catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removezeroafterresult(result: String): String{
        var temp = result
        if(result.contains(".0")){
            temp = result.substring(0, result.length - 2)
        }
        return temp
    }

    fun onoperator(view: View){
        tvInput?.text?.let {
            if(lastnumeric && !isoperator(it.toString())){
                tvInput?.append((view as Button).text)
                lastdot=false
                lastnumeric=false
            }
        }
    }

    private fun isoperator(value: String): Boolean{
        return if(value.startsWith("-")) {
            false
        }else{
            value.contains("+")
                    || value.contains("-")
                    ||value.contains("*")
                    ||value.contains("/")
        }
    }
}