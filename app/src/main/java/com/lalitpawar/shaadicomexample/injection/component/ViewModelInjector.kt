package com.lalitpawar.shaadicomexample.injection.component


import com.lalitpawar.shaadicomexample.injection.module.NetworkModule
import com.lalitpawar.shaadicomexample.view_model.MainViewModel
import com.lalitpawar.shaadicomexample.view_model.ProfileItemViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(mainViewModel: MainViewModel)
    fun inject(profileItemViewModel: ProfileItemViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}