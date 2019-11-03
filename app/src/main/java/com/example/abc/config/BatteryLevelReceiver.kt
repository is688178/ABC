package com.example.abc.config

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class BatteryLevelReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        when {
            intent != null -> {
                if(context != null)
                    showLowBatteryDialog(context)
            }
        }
    }

    private fun showLowBatteryDialog(context: Context) {
        val builder = AlertDialog.Builder(context)

        // Set the alert dialog title
        builder.setTitle("Advertencia")

        // Display a message on alert dialog
        builder.setMessage("Bateria baja")

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("OK") { _, _ ->
            Toast.makeText(
                context,
                "Considera conectar tu dispositivo",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }
}