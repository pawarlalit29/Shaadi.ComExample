package com.lalitpawar.shaadicomexample.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lalitpawar.shaadicomexample.model.ProfilePref
import com.lalitpawar.shaadicomexample.utils.Profile_Table
import io.reactivex.Maybe


@Dao
interface ProfileDao {
    @get:Query("SELECT * FROM $Profile_Table")
    val all: List<ProfilePref>

    @Insert
    fun insertAll(vararg profilePref: ProfilePref)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfilePref(profilePref: ProfilePref)



}