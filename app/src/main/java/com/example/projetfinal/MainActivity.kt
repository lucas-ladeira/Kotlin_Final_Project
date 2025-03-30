package com.example.projetfinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.content.res.Configuration
import java.util.*

class MainActivity : BaseActivity() {
    private lateinit var englishButton: Button
    private lateinit var frenchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        englishButton = findViewById(R.id.englishButton)
        frenchButton = findViewById(R.id.frenchButton)

        englishButton.setOnClickListener {
            setAppLanguage("en") // Cambia a inglés
            reloadApp()
        }

        frenchButton.setOnClickListener {
            setAppLanguage("fr") // Cambia a francés
            reloadApp()
        }
    }

    private fun setAppLanguage(languageCode: String) {
        val sharedPref = getSharedPreferences("Settings", MODE_PRIVATE)
        sharedPref.edit().putString("app_lang", languageCode).apply()
    }

    private fun reloadApp() {
        val intent = Intent(this, CropListActivity::class.java)
        startActivity(intent)
        finish() // Cierra MainActivity para forzar la recarga
    }
}