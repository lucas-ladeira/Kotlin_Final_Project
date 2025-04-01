package com.example.projetfinal

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationHelper(private val context: Context) {
    companion object {
        // Identifiants constants pour le canal de notification et les notifications
        const val CHANNEL_ID = "temperature_alerts_channel"
        const val NOTIFICATION_ID = 1

    }

init {
    createNotificationChannel()
}

    private fun createNotificationChannel() {
        // Vérification de la version d'Android (les canaux ne sont nécessaires qu'à partir d'Oreo)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Configuration du canal
            val name = "Alertes de température"
            val descriptionText = "Notifications lorsque la température dépasse la temperature adequate"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
                // Ici vous pourriez ajouter d'autres configurations comme le son, la vibration, etc.
            }

            // Enregistrement du canal auprès du système
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showTemperatureAlert(crop: Crop, isTooHigh: Boolean) {
        // Intent pour ouvrir l'application quand on clique sur la notification

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        // PendingIntent pour l'action de clic
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Construction du message en fonction du type d'alerte
        val message = if (isTooHigh) {
            "Température trop élevée pour '${crop.name}' (Max: ${crop.maxTemperature}°C)"
        } else {
            "Température trop basse pour '${crop.name}' (Min: ${crop.minTemperature}°C)"
        }

        // Construction de la notification
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.santa) // Icône de la notification
            .setContentTitle("Alerte de température") // Titre
            .setContentText(message) // Message principal
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Priorité haute
            .setContentIntent(pendingIntent) // Action au clic
            .setAutoCancel(true) // La notification disparaît au clic
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC) // Visible sur l'écran verrouillé

        // Affichage de la notification
        with(NotificationManagerCompat.from(context)) {
            // Utilisation de l'ID de la tâche comme ID de notification pour éviter les doublons


            //notify (crop.id, builder.build())

            //OJO PENDIENTE CORREGIR EL ID
        }
    }
}