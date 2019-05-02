package com.valid.jobtimer.views

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.valid.jobtimer.R
import com.valid.jobtimer.viewmodels.TimesViewModel
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.detail_list_item.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val viewModel by viewModel<TimesViewModel>()
    private lateinit var days: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBack.setOnClickListener {
            findNavController().navigate(R.id.toMain, null)
        }

        viewModel.weekTimeMissing.observe(this, Observer {
            tvTimeMissing.text = it.toString()
        })

        days = resources.getStringArray(R.array.days)

        for (day in days) {
            llDays.addView(createDayView(day))
        }
    }

    private fun createDayView(day: String): View {
        val dayBtn = LayoutInflater.from(context).inflate(R.layout.detail_list_item, null)
        dayBtn.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f)
        dayBtn.tvDay.text = day

        dayBtn.tvArrival.text = viewModel.getDayArrival(day)
        dayBtn.tvArrival.setOnClickListener { showEditDialog("Arrival", day) }

        dayBtn.tvDeparture.text = viewModel.getDayDeparture(day)
        dayBtn.tvDeparture.setOnClickListener { showEditDialog("Departure", day) }

        return dayBtn
    }

    private fun showEditDialog(type: String, day: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Enter new $type time for $day")

        val input = EditText(context)

        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        builder.setView(input)

        builder.setPositiveButton("OK") { _, _ ->
            run {
                this.viewModel.setTime(type, day, input.text.toString())
                if (type == "Arrival") {
                    llDays.getChildAt(days.indexOf(day)).tvArrival.text = input.text.toString()
                } else {
                    llDays.getChildAt(days.indexOf(day)).tvDeparture.text = input.text.toString()
                }
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

}
