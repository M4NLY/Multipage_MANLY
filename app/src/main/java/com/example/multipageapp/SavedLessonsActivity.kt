package com.example.multipageapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class SavedLessonsActivity : AppCompatActivity() {

    private lateinit var savedLessons: MutableList<Lesson>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_lessons)

        val listView: ListView = findViewById(R.id.savedLessonsListView)
        val backButton: Button = findViewById(R.id.backButton)

        loadSavedLessons()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, savedLessons.map { it.title })
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedLesson = savedLessons[position]
            val intent = Intent(this, LessonDetailActivity::class.java).apply {
                putExtra("lessonTitle", selectedLesson.title)
                putExtra("lessonDescription", selectedLesson.description)
            }
            startActivity(intent)
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val selectedLesson = savedLessons[position]
            AlertDialog.Builder(this).apply {
                setTitle("Delete Lesson")
                setMessage("Are you sure you want to delete the lesson: ${selectedLesson.title}?")
                setPositiveButton("Yes") { _, _ ->
                    savedLessons.removeAt(position)
                    saveLessonsToFile()
                    adapter.notifyDataSetChanged()
                }
                setNegativeButton("No", null)
            }.show()
            true
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun loadSavedLessons() {
        val file = File(filesDir, "saved")
        if (file.exists()) {
            ObjectInputStream(FileInputStream(file)).use { input ->
                @Suppress("UNCHECKED_CAST")
                savedLessons = input.readObject() as MutableList<Lesson>
            }
        } else {
            savedLessons = mutableListOf()
        }
    }

    private fun saveLessonsToFile() {
        val file = File(filesDir, "saved")
        ObjectOutputStream(FileOutputStream(file)).use { output ->
            output.writeObject(savedLessons)
        }
    }
}