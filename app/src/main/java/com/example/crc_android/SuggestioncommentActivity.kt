package com.example.crc_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class SuggestioncommentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestioncomment)

        val prevbtn1 = findViewById<Button>(R.id.leftarrow) as ImageButton
        prevbtn1.setOnClickListener {
            val myIntent = Intent(this, SuggestionActivity::class.java)
            startActivity(myIntent)
        }
        val addbtn = findViewById<Button>(R.id.add1) as Button
        addbtn.setOnClickListener {
            val myIntent = Intent(this, SuggestioncontentActivity::class.java)
            startActivity(myIntent)
        }
    }
}