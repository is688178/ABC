package com.example.abc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.abc.config.SESSION_ID_KEY
import com.example.abc.config.SHARED_PREFERENCES
import com.parse.ParseInstallation
import com.parse.ParseUser
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


class ActivityLogin : AppCompatActivity() {
    private lateinit var mTextEmail: EditText
    private lateinit var mTextPassword: EditText
    private lateinit var mButtonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide() //hide the title bar
        setContentView(R.layout.activity_login)

        //Check for current session
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE) //"com.eventime.SHARED_PREFERENCES"
        val sessionId = sharedPreferences.getString(SESSION_ID_KEY, "")

        if (sessionId != "") {
            val intent = Intent(this, ActivityMain::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }

        //If there is no current session, continue with LogIn
        mTextEmail = find(R.id.activity_login_tiet_email)
        mTextPassword = find(R.id.activity_login_tiet_password)
        mButtonLogin = find(R.id.activity_login_btn_login)

        mButtonLogin.setOnClickListener {
            val strEmail = mTextEmail.text.toString().trim()
            val strPassword = mTextPassword.text.toString().trim()

            ParseUser.logInInBackground(strEmail, strPassword)
            { parseUser, error ->
                if (error == null) {
                    //Sign up successful
                    Log.d("PARSE", "Log in successful email: $strEmail")
                    saveSessionToken(parseUser.sessionToken)
                    startActivity<ActivityMain>()
                } else {
                    //There was an error,
                    Log.e(
                        "DEBUG PARSE",
                        "Failed to complete log in process. Error message: ${error.message} Error code ${error.code}"
                    )
                    showErrorDialog()
                }
            }

        }

        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground()
    }

    private fun saveSessionToken(sessionToken: String) {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SESSION_ID_KEY, sessionToken)
        editor.apply()
    }

    private fun showErrorDialog() {
        val builder = AlertDialog.Builder(this)

        // Set the alert dialog title
        builder.setTitle("Error de Inicio de Sesión")

        // Display a message on alert dialog
        builder.setMessage("Favor de comprobar su Email/Contraseña")

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("OK") { _, _ ->
            Toast.makeText(
                this,
                "Revisa y vuelve a presionar el boton....",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }
}