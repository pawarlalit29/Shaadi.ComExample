package com.lalitpawar.shaadicomexample.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.lalitpawar.shaadicomexample.model.database.AppDatabase
import com.lalitpawar.shaadicomexample.view_model.ProfileItemViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileItemViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "profile").build()
            @Suppress("UNCHECKED_CAST")
            return ProfileItemViewModel(db.profilePrefDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}