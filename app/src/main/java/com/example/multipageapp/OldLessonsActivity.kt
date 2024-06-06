package com.example.multipageapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class OldLessonsActivity : AppCompatActivity() {
    private val lessons = arrayOf(
        "WordNest",
        "A Little Game",
        "Corvid Run",
        "Responsive Website",
        "Art Exchange"
    )
    private val descriptions = arrayOf(
        "WordNest is an application that was made for a group activity, For this group activity we had to create an application so we had decided on making a DICTIONARY.",
        "A Little Game was a story game I had created for one of our assessment the non-linear story making.",
        "The game I have created is named Corvid Run. Corvid Run is a very simple game that was unintentionally inspired by the old platform shooter games such as Contra by Nintendo. Corvid Run was originally planned to only have a simple dash as its main game mechanic, however in the process of making the enemy sprites, I had the idea of the player character of the game being able to beat them. After I had thought of making the player be able to beat enemies I originally wanted for the player to have a close range melee attack instead of the feather bullets I now have in my game originally inspired by another Nintendo game Metroid.",
        "One of our old assessments was the few tasks that we had to recreate a page just like this.",
        "The website I am currently using for this assessment is the same website I had created before for a previous assessment. During the second semester for level 4 Creative Computing we had to create a website that could go to different pages whenever we clicked a button we made on the website. As for this assessment I did not make all of the pages of my old website be able to change depending on the screen size, so I did not bother to attach them onto my website."

    )
    private val imageUris = arrayOf(
        "android.resource://com.example.multipageapp/drawable/wordnest",
        "android.resource://com.example.multipageapp/drawable/littlegame",
        "android.resource://com.example.multipageapp/drawable/corvid",
        "android.resource://com.example.multipageapp/drawable/responsive",
        "android.resource://com.example.multipageapp/drawable/artexchange"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_old_lessons)

        val listView: ListView = findViewById(R.id.listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lessons)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, LessonDetailActivity::class.java).apply {
                putExtra("lessonTitle", lessons[position])
                putExtra("lessonDescription", descriptions[position])
                putExtra("lessonImageUri", imageUris[position])
            }
            startActivity(intent)
        }
    }
}