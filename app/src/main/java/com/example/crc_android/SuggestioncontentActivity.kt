package com.example.crc_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class SuggestioncontentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestioncontent)

        val prevbtn1 = findViewById<Button>(R.id.leftarrow) as ImageButton
        prevbtn1.setOnClickListener {
            val myIntent = Intent(this, SuggestionActivity::class.java)
            startActivity(myIntent)
        }

        val finishbtn = findViewById<Button>(R.id.finish) as Button
        finishbtn.setOnClickListener {
            val myIntent = Intent(this, SuggestionActivity::class.java)
            startActivity(myIntent)
        }

        //todo edittext누르면 intent
        val commentedit = findViewById<EditText>(R.id.comment) as TextView
        finishbtn.setOnClickListener {
            val myIntent = Intent(this, SuggestioncommentActivity::class.java)
            startActivity(myIntent)
        }
    }
}