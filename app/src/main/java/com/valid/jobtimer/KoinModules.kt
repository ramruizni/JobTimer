package com.valid.jobtimer

import com.valid.jobtimer.viewmodels.MainViewModel
import org.koin.dsl.module.module

val mainModule = module {
    single { MainViewModel() }
}
