package com.example.crc_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class RegistractionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registraction)

        val prevbtn = findViewById<Button>(R.id.leftarrow) as ImageButton
        prevbtn.setOnClickListener {
            val myIntent = Intent(this,SuggestionActivity::class.java)
            startActivity(myIntent)
        }

        val updatebtn = findViewById<Button>(R.id.update) as Button
        updatebtn.setOnClickListener {
            val myIntent1 = Intent(this, SuggestioncontentActivity::class.java)
            startActivity(myIntent1)
        }
    }
}