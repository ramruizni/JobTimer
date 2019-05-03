package com.valid.jobtimer.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.valid.jobtimer.R
import com.valid.jobtimer.viewmodels.TimesViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {

    private val viewModel: TimesViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCount.setOnClickListener {
            findNavController().navigate(R.id.toDetail, null)
        }

        viewModel.weekTimeMissing.observe(this, Observer { tvCount.text = it.toString() })

        btnArrival.setOnClickListener { viewModel.setTime("Arrival") }
        btnDeparture.setOnClickListener { viewModel.setTime("Departure") }
    }

}
