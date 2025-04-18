package com.example.projetfinal

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {

        val lang = newBase.getSharedPreferences("Settings", MODE_PRIVATE)
            .getString("app_lang", "en") ?: "en"

        super.attachBaseContext(updateLocale(newBase, lang))
    }

    private fun updateLocale(context: Context, lang: String): Context {
        Locale.setDefault(Locale(lang))// locale es class java

        //Aplicar a la configuración
        val config = context.resources.configuration
        config.setLocale(Locale(lang))

        //Devolver nuevo contexto con la configuración
        return context.createConfigurationContext(config)
    }
}