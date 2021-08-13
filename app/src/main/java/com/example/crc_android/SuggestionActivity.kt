package com.example.crc_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.cardview.widget.CardView

class SuggestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestion)

        val plusBtn = findViewById<Button>(R.id.plus) as ImageButton
        plusBtn.setOnClickListener {
            val myIntent = Intent(this, RegistractionActivity::class.java)
            startActivity(myIntent)
        }

        val board1btn = findViewById<CardView>(R.id.board1) as CardView
        board1btn.setOnClickListener{
            val contentIntent = Intent(this, SuggestioncontentActivity::class.java)
            startActivity(contentIntent)
        }
    }

}