package com.bkm.myapplication

import Models.Flags
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var flagArrayList: ArrayList<Flags>
    var count = 0
    var countryName = ""
    lateinit var buttonArrayList: ArrayList<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonArrayList = ArrayList()

        createObject()
        btnLocateCount()
    }

    private fun createObject() {
        flagArrayList = ArrayList()
        flagArrayList.add(Flags("turkey", R.drawable.turkey))
        flagArrayList.add(Flags("canada", R.drawable.canada))
        flagArrayList.add(Flags("australia", R.drawable.australia))
        flagArrayList.add(Flags("china", R.drawable.china))
        flagArrayList.add(Flags("france", R.drawable.france))
        flagArrayList.add(Flags("germany", R.drawable.germany))
        flagArrayList.add(Flags("india", R.drawable.india))
        flagArrayList.add(Flags("italy", R.drawable.italy))
        flagArrayList.add(Flags("mexico", R.drawable.mexico))
        flagArrayList.add(Flags("spain", R.drawable.spain))
        flagArrayList.add(Flags("uzbekistan", R.drawable.uzbekistan))
        flagArrayList.add(Flags("england", R.drawable.ukking))
        flagArrayList.add(Flags("usa", R.drawable.usa))
        flagArrayList.add(Flags("russia", R.drawable.russia))
    }

    private fun btnLocateCount() {
        image_1.setImageResource(flagArrayList[count].image)
        liner_text.removeAllViews()
        liner_btn_1.removeAllViews()
        liner_btn_2.removeAllViews()
        countryName = ""
        putBtn(flagArrayList[count].name)
    }


    private fun putBtn(countryName: String) {
        val btnArray: ArrayList<Button> = randomBtn(countryName)
        for (i in 0..5) {
            liner_btn_1.addView(btnArray[i])
        }
        for (i in 6..11) {
            liner_btn_2.addView(btnArray[i])
        }
    }


    private fun randomBtn(countryName: String): java.util.ArrayList<Button> {
        val array = ArrayList<Button>()
        val arrayText = ArrayList<String>()

        for (c in countryName) {
            arrayText.add(c.toString())
        }

        if (arrayText.size != 12) {
            val str = "ABCDEFGHIJKLMNOPQRSTUVXYZ"
            for (i in arrayText.size until 12) {
                val random = Random().nextInt(str.length)
                arrayText.add(str[random].toString())
            }
        }
        arrayText.shuffle()
        for (i in 0 until arrayText.size) {
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )
            button.text = arrayText[i]
            button.setOnClickListener(this)
            array.add(button)
        }
        return array
    }

    override fun onClick(v: View?) {
        val button1 = v as Button
        if (buttonArrayList.contains(button1)) {
            liner_text.removeView(button1)
            var hasCountry = false
            liner_btn_1.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    if (!hasCountry) {
                        countryName = countryName.substring(0, countryName.length - 1)
                        hasCountry = true
                    }
                }
            }
            liner_btn_2.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    if (!hasCountry) {
                        countryName = countryName.substring(0, countryName.length - 1)
                        hasCountry = true
                    }
                }
            }
        } else {
            button1.visibility = View.INVISIBLE
            countryName += button1.text.toString().uppercase(Locale.getDefault())
            val button2 = Button(this)
            button2.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )
            button2.text = button1.text
            button2.setOnClickListener(this)
            buttonArrayList.add(button2)
            liner_text.addView(button2)
            checkText()
        }
    }

    private fun checkText() {
        if (countryName == flagArrayList[count].name.uppercase(Locale.getDefault())) {
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
            if (count == flagArrayList.size - 1) {
                count = 0
            } else {
                count++

            }
            btnLocateCount()
        } else {
            if (countryName.length == flagArrayList[count].name.length) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                liner_text.removeAllViews()
                liner_btn_2.removeAllViews()
                liner_btn_1.removeAllViews()
                countryName = ""
                putBtn(flagArrayList[count].name)
                countryName = ""
            }
        }
    }
}


