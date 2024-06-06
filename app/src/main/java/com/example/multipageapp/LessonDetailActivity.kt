package com.example.multipageapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*

data class Lesson(val title: String, val description: String) : java.io.Serializable

class LessonDetailActivity : AppCompatActivity() {
    private lateinit var savedLessons: MutableList<Lesson>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_detail)

        val lessonTitle: TextView = findViewById(R.id.lessonTitle)
        val lessonDescription: TextView = findViewById(R.id.lessonDescription)
        val lessonImage: ImageView = findViewById(R.id.lessonImage) // Find the ImageView
        val backButton: Button = findViewById(R.id.backButton)
        val saveButton: Button = findViewById(R.id.saveButton)

        val title = intent.getStringExtra("lessonTitle") ?: "No Title"
        val description = intent.getStringExtra("lessonDescription") ?: "No Description"
        val imageUriString = intent.getStringExtra("lessonImageUri") // Get the image URI

        lessonTitle.text = title
        lessonDescription.text = description

        // Load and display the image
        if (!imageUriString.isNullOrEmpty()) {
            val imageUri = Uri.parse(imageUriString)
            lessonImage.setImageURI(imageUri)
        }

        backButton.setOnClickListener {
            val intent = Intent(this, OldLessonsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        saveButton.setOnClickListener {
            val lesson = Lesson(title, description)
            saveLesson(lesson)
        }

        loadSavedLessons()
    }

    private fun loadSavedLessons() {
        val file = File(filesDir, "saved")
        savedLessons = if (file.exists()) {
            ObjectInputStream(FileInputStream(file)).use { input ->
                @Suppress("UNCHECKED_CAST")
                input.readObject() as MutableList<Lesson>
            }
        } else {
            mutableListOf()
        }
    }

    private fun saveLesson(lesson: Lesson) {
        savedLessons.add(lesson)
        val file = File(filesDir, "saved")
        ObjectOutputStream(FileOutputStream(file)).use { output ->
            output.writeObject(savedLessons)
        }
    }
}