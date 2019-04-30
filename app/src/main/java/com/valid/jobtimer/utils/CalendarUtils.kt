package com.valid.jobtimer.utils

import androidx.fragment.app.FragmentActivity
import java.text.SimpleDateFormat
import java.util.*

class CalendarUtils {
    companion object {

        private const val PREFS_FILENAME = "com.makuhita.jobtimer.prefs"
        var format = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US)

        fun getDayString(date: Date): String {
            return when(date.toString().subSequence(0, 2)) {
                    "Mon" -> "Monday"
                    "Tue" -> "Tuesday"
                    "Wed" -> "Wednesday"
                    "Thu" -> "Thursday"
                    "Fri" -> "Friday"
                    "Sat" -> "Saturday"
                else -> {
                    "Sunday"
                }
            }
        }

        fun saveTime(activity: FragmentActivity, type: String) {
            val date = Date().time

            val editor = activity.getSharedPreferences(PREFS_FILENAME, 0).edit()
            editor.putLong("$type - Monday", date)
            editor.apply()
        }

        fun getTime(activity: FragmentActivity, type: String): Long {
            val prefs = activity.getSharedPreferences(PREFS_FILENAME, 0)
            val date = prefs.getLong("$type - Monday", 0)
            return date
        }

    }

}
