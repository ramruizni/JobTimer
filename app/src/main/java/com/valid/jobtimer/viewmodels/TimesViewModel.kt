package com.valid.jobtimer.viewmodels

import android.os.Handler
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valid.jobtimer.utils.CalendarUtils
import java.text.SimpleDateFormat
import java.util.*

class TimesViewModel : ViewModel() {

    var weekTimeMissing = MutableLiveData<String>()

    val handler = Handler()

    init {
        weekTimeMissing.value = ""
    }

    fun startTimer(activity: FragmentActivity) {
        handler.postDelayed(object: Runnable {
            override fun run() {

                //weekTimeMissing.value = SimpleDateFormat("HH:mm:ss", Locale.US).format(Calendar.getInstance().time)
                updateWeekTimeMissing(activity)

                handler.postDelayed(this, 1000)
            }
        }, 0)
    }

    fun setDayArrival(activity: FragmentActivity) {
        CalendarUtils.saveTime(activity, "Arrival")
    }

    fun setDayDeparture(activity: FragmentActivity) {
        CalendarUtils.saveTime(activity, "Departure")
    }

    fun updateWeekTimeMissing(activity: FragmentActivity) {
        val arrival = CalendarUtils.getTime(activity, "Arrival")
        val departure = CalendarUtils.getTime(activity, "Departure")

        weekTimeMissing.value = SimpleDateFormat("HH:mm:ss", Locale.US).format(departure - arrival)
    }
}
