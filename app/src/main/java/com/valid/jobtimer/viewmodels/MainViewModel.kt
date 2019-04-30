package com.valid.jobtimer.viewmodels

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {

    var weeklyHoursMissing = MutableLiveData<Date>()
    var dayArrival = MutableLiveData<Date>()
    var dayDeparture = MutableLiveData<Date>()

    val handler = Handler()

    init {
        weeklyHoursMissing.value = Calendar.getInstance().time
        dayArrival.value = null
        dayDeparture.value = null
    }

    fun startTimer() {
        handler.postDelayed(object: Runnable {
            override fun run() {

                handler.postDelayed(this, 1000)
            }
        }, 0)


    }
}
