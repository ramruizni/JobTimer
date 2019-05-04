package com.valid.jobtimer.utils

import android.app.Application
import android.util.Log
import com.valid.jobtimer.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class CalendarUtils(var application: Application) {

    private val PREFS_FILENAME: String = "com.makuhita.jobtimer.prefs"
    private val days = application.resources.getStringArray(R.array.days)

    private fun getCurrentDayString(): String {
        Log.e("asd", Date().toString().subSequence(0, 3).toString())
        return when (Date().toString().subSequence(0, 3)) {
            "Mon" -> days[0]
            "Tue" -> days[1]
            "Wed" -> days[2]
            "Thu" -> days[3]
            "Fri" -> days[4]
            else -> {
                days[5]
            }
        }
    }

    fun saveTimeMillis(type: String, day: String, time: String) {
        val editor = application.getSharedPreferences(PREFS_FILENAME, 0).edit()
        editor.putLong("$type - $day", SimpleDateFormat("HH:mm:ss", Locale.US).parse(time).time)
        editor.apply()
    }

    fun saveTimeMillis(type: String) {
        saveTimeMillis(type, getCurrentDayString(), SimpleDateFormat("HH:mm:ss", Locale.US).format(Date().time))
    }

    fun getTimeMillis(type: String, day: String): Long {
        val prefs = application.getSharedPreferences(PREFS_FILENAME, 0)
        return prefs.getLong("$type - $day", 0L)
    }

    fun getTime(type: String, day: String): String {
        return if (getTimeMillis(type, day) == 0L) {
            "-"
        } else {
            SimpleDateFormat("HH:mm:ss", Locale.US).format(Date(getTimeMillis(type, day)))
        }
    }

    fun getDayTimeMillisWorked(day: String): Long {
        val arrival = getTimeMillis("Arrival", day)
        val departure = getTimeMillis("Departure", day)

        return if (arrival == 0L) {
            0
        } else if (departure == 0L && day == getCurrentDayString()) {
            Date().time - arrival
        } else {
            departure - arrival
        }
    }

    fun getWeekTimeMissing(): String {
        var weekTimeMissing = TimeUnit.HOURS.toMillis(45)
        for (day in days) {
            weekTimeMissing -= getDayTimeMillisWorked(day)
            Log.e("asd", "$day: ${getTimeMillis("Arrival", day)} - ${getTimeMillis("Departure", day)}")
        }
        Log.e("asd", "-")
        return millisToFormattedString(weekTimeMissing)
    }

    fun millisToFormattedString(milliseconds: Long): String {
        var millis = milliseconds

        val hours = TimeUnit.MILLISECONDS.toHours(millis)
        millis -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
        millis -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis)

        return "$hours:$minutes:$seconds"
    }

}
