package com.example.projetfinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var englishButton: Button
    private lateinit var frenchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        englishButton = findViewById(R.id.englishButton)
        frenchButton = findViewById(R.id.frenchButton)

        englishButton.setOnClickListener {
            // TODO: Handle English button click
            val intent = Intent(this, CropListActivity::class.java)
            startActivity(intent)
        }

        frenchButton.setOnClickListener {
            // TODO: Handle French button click
            Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }
    }
}