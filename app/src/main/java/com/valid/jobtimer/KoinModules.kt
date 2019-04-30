package com.valid.jobtimer

import com.valid.jobtimer.viewmodels.TimesViewModel
import org.koin.dsl.module.module

val timesModule = module {
    single { TimesViewModel() }
}

