package com.paloit.validator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    var numbersByElement : Map<Int,Int> ? = null
    private var listNumbers : ArrayList<Int> ? = null
    var validation : String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
        listNumbers = ArrayList()

    }

    private fun setListeners() {
        btn_lucknumber.setOnClickListener {
            onClickBtnLuckyNumber(it)
        }
    }


    fun onClickBtnLuckyNumber(v : View){
        if(checkValidation(et_cardnuber.text.toString())){ validation = "true" }else{ validation = "false" }
        Toast.makeText(this@MainActivity, validation, Toast.LENGTH_LONG).show()
        var number : String = mostRepitNumber(et_cardnuber.text.toString())
        tv_description.setText("Número de la suerte: " + number[1])
    }

    fun checkValidation(str : String) : Boolean{
        if ( (str.length>=14) and (str.length<=16) ){ return true }
        return false
    }

    fun mostRepitNumber(str : String) : String{

        for(i in str){ listNumbers?.add(i.toString().toInt()) }

        numbersByElement = listNumbers?.groupingBy { it }?.eachCount()
        println("Numbers and count of many repeats")
        /// Log Print to each numbers
        println(numbersByElement)

        // This is an optional function but i don't want to use it beacuse increments algorithm complex O(n2)
        // I preffer to use android library functions
       /* for (i in mutableList!!){
            var cont = 1
            for (j in listNumbers!!){
                if (i==j){
                    cont++
                }
                Log.d("number " +i, cont.toString())
            }
        }*/

        var mostrepetitivenumber = listNumbers?.groupingBy { it }?.eachCount()?.toList()?.sortedByDescending { it.second }?.take(1)

        return mostrepetitivenumber?.first().toString()
    }

}