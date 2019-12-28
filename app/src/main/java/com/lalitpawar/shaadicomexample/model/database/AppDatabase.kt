package com.lalitpawar.shaadicomexample.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lalitpawar.shaadicomexample.model.ProfilePref
import com.lalitpawar.shaadicomexample.model.dao.ProfileDao

@Database(entities = [ProfilePref::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profilePrefDao(): ProfileDao
}