package com.lalitpawar.shaadicomexample.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class ShaadiComApplication : Application() {
    companion object {
        lateinit var instance: ShaadiComApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

}