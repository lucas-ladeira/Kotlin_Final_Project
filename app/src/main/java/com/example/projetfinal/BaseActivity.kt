package com.example.projetfinal

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {

        val sharedPref = newBase.getSharedPreferences("Settings", MODE_PRIVATE)
        val lang = sharedPref.getString("app_lang", "en") ?: "en"
        super.attachBaseContext(setLocale(newBase, lang))
    }

    // Función para cambiar el idioma
    fun setLocale(context: Context, languageCode: String): Context {

        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration (context.resources.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }
}