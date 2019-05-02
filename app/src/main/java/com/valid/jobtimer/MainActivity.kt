package com.valid.jobtimer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val PREFS_FILENAME = "com.makuhita.jobtimer.prefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //wipePrefs()
    }

    fun wipePrefs() {
        application.getSharedPreferences(PREFS_FILENAME, 0).edit().clear().apply()
    }
}
