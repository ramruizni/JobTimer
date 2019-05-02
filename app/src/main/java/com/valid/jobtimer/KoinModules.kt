package com.valid.jobtimer

import com.valid.jobtimer.utils.CalendarUtils
import com.valid.jobtimer.viewmodels.TimesViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val timesModule = module {
    viewModel { TimesViewModel(CalendarUtils(get())) }
}
