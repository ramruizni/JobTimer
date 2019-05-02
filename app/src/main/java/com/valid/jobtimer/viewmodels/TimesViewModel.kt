package com.valid.jobtimer.viewmodels

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valid.jobtimer.utils.CalendarUtils

class TimesViewModel(var calendarUtils: CalendarUtils) : ViewModel() {

    var weekTimeMissing = MutableLiveData<String>()
    val handler = Handler()

    init {
        weekTimeMissing.value = ""
        startTimer()
    }

    private fun startTimer() {
        handler.postDelayed(object: Runnable {
            override fun run() {
                updateWeekTimeMissing()
                handler.postDelayed(this, 1000)
            }
        }, 0)
    }

    fun setTime(type: String, day: String, time: String) {
        calendarUtils.saveTimeMillis(type, day, time.toLong())
    }

    fun setTime(type: String) {
        calendarUtils.saveTimeMillis(type)
    }

    fun getDayArrival(day: String): String {
        return calendarUtils.getTime("Arrival", day)
    }

    fun getDayDeparture(day: String): String {
        return calendarUtils.getTime("Departure", day)
    }

    fun updateWeekTimeMissing() {
        weekTimeMissing.value = calendarUtils.getWeekTimeMissing()
    }
}
