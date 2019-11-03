package com.example.abc

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    companion object{
        const val SPLASH_TIME_OUT: Long = 3000 // 3 sec
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide() //hide the title bar
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this, ActivityMain::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}