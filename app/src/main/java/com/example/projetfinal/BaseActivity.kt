package com.example.projetfinal

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

open class BaseActivity : AppCompatActivity() { // open parce que permett heritage

    override fun attachBaseContext(newBase: Context) {

        //Activité cree pour le changement de langue, et faire heritait de cette activité

        val sharedPref = newBase.getSharedPreferences("Settings", MODE_PRIVATE)

        val lang = sharedPref.getString("app_lang", "en") ?: "en" // doble seguridad
        //obtengo el valor de la llave app_lang
        super.attachBaseContext(setLocale(newBase, lang))
        // el super llama el metodo de la clase padre, el setlocale change la langue
    }



    // Función para cambiar el idioma
    fun setLocale(context: Context, languageCode: String): Context {

        val locale = Locale(languageCode) //locale class de java -congfiguracion regional
        Locale.setDefault(locale) // etabli predetermine


        val config = Configuration (context.resources.configuration) //configuration class de android, prendre actuel
        config.setLocale(locale)// apli lcoale
        return context.createConfigurationContext(config) //return context nouvell config
    }
}