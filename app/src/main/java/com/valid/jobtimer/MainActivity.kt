package com.valid.jobtimer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.valid.jobtimer.viewmodels.TimesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // Shared between fragments
    val viewModel by viewModel<TimesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun wipePrefs() {
        application.getSharedPreferences(PREFS_FILENAME, 0).edit().clear().apply()
    }

    companion object {
        private const val PREFS_FILENAME = "com.makuhita.jobtimer.prefs"
    }
}
