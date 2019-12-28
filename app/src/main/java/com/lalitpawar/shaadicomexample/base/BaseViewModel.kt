package com.lalitpawar.shaadicomexample.base

import androidx.lifecycle.ViewModel
import com.lalitpawar.shaadicomexample.injection.component.DaggerViewModelInjector
import com.lalitpawar.shaadicomexample.injection.component.ViewModelInjector
import com.lalitpawar.shaadicomexample.injection.module.NetworkModule
import com.lalitpawar.shaadicomexample.view_model.MainViewModel
import com.lalitpawar.shaadicomexample.view_model.ProfileItemViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainViewModel -> injector.inject(this)
            is ProfileItemViewModel -> injector.inject(this)
        }
    }
}