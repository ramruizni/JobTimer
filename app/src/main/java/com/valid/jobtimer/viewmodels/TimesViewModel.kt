package com.valid.jobtimer.viewmodels

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valid.jobtimer.utils.CalendarUtils
import java.text.SimpleDateFormat
import java.util.*

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
        calendarUtils.saveTimeMillis(type, day, time)
    }

    fun setTime(type: String) {
        calendarUtils.saveTimeMillis(type)
    }

    fun getTime(type: String, day: String): String {
        return calendarUtils.getTime(type, day)
    }

    fun updateWeekTimeMissing() {
        weekTimeMissing.value = calendarUtils.getWeekTimeMissing()
    }

    fun deleteTime(type: String, day: String) {
        calendarUtils.deleteTime(type, day)
    }
}
