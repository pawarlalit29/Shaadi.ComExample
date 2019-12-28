package com.lalitpawar.shaadicomexample.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lalitpawar.shaadicomexample.utils.Profile_Table

@Entity(tableName = "$Profile_Table")
data class ProfilePref(
    @field:PrimaryKey
    var userId: String,
    var acceptStatus : Boolean

)