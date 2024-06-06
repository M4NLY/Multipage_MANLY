package com.example.multipageapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveButton: Button = findViewById(R.id.saveButton)
        val oldLessonsButton: Button = findViewById(R.id.oldLessonsButton)


        saveButton.setOnClickListener {
            val intent = Intent(this, SavedLessonsActivity::class.java)
            startActivity(intent)
        }

        oldLessonsButton.setOnClickListener {
            val intent = Intent(this, OldLessonsActivity::class.java)
            startActivity(intent)
        }
    }
}