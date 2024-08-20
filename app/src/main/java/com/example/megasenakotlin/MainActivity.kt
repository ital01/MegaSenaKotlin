package com.example.megasenakotlin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val dbHelper by lazy { NumbersDBHelper(this) }
    private val handler = Handler(Looper.getMainLooper())
    private var isButtonClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars: Insets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.button_display).setOnClickListener {
            if (isButtonClickable) {
                isButtonClickable = false
                handler.postDelayed({
                    displayNumbers()
                    isButtonClickable = true
                }, 500)
            }
        }

        findViewById<Button>(R.id.button_go_to_list).setOnClickListener {
            val intent = Intent(this, NumbersListActivity::class.java)
            startActivity(intent)
        }

    }

    private fun displayNumbers() {
        val numbers = Numbers.generateNumbers()
        numbers.forEachIndexed { index, number ->
            val textView = findViewById<TextView>(resources.getIdentifier("n${index + 1}", "id", packageName))
            textView.text = number.toString()
        }
        dbHelper.insertNumbers(numbers)
    }
}
