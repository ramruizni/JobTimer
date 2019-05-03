package com.valid.jobtimer.views

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.valid.jobtimer.R
import com.valid.jobtimer.viewmodels.TimesViewModel
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.detail_list_item.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class DetailFragment : Fragment() {

    private val viewModel: TimesViewModel by sharedViewModel()
    private lateinit var days: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBack.setOnClickListener { findNavController().navigate(R.id.toMain, null) }

        viewModel.weekTimeMissing.observe(this, Observer { tvTimeMissing.text = it.toString() })

        days = resources.getStringArray(R.array.days)

        for (day in days) {
            llDays.addView(createDayView(day))
        }
    }

    private fun createDayView(day: String): View {
        val dayBtn = LayoutInflater.from(context).inflate(R.layout.detail_list_item, null)
        dayBtn.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f)
        dayBtn.tvDay.text = day

        dayBtn.tvArrival.text = viewModel.getTime("Arrival", day)
        dayBtn.tvArrival.setOnClickListener { showEditDialog(dayBtn.tvArrival,"Arrival", day) }

        dayBtn.tvDeparture.text = viewModel.getTime("Departure", day)
        dayBtn.tvDeparture.setOnClickListener { showEditDialog(dayBtn.tvDeparture,"Departure", day) }

        return dayBtn
    }

    private fun showEditDialog(textView: TextView, type: String, day: String) {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(context,
            TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                val timeToSave = "$selectedHour:$selectedMinute:00"
                textView.text = timeToSave
                viewModel.setTime(type, day, timeToSave)
            }, hour, minute, true
        )
        mTimePicker.setTitle("Select Time")
        mTimePicker.show()
    }
}
