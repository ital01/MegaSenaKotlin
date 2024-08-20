package com.example.megasenakotlin

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NumbersListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.numbers_list)

        val textViewNumbers = findViewById<TextView>(R.id.textViewNumbers)
        val dbHelper = NumbersDBHelper(this)

        val numbersString = dbHelper.retrieveAllNumbers()
            .mapIndexed { index, numbers ->
                val drawID = index + 1
                " Draw ${"%3d".format(drawID)} : ${numbers.joinToString(" - ")}\n\n"
            }.joinToString("")

        textViewNumbers.text = numbersString

        findViewById<Button>(R.id.button_return).setOnClickListener {
            returnMain()
        }
    }

    private fun returnMain() {
        finish()
    }
}
