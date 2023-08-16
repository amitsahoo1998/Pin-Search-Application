package com.neoapp.pinsearch.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "PinTable")
data class PinCodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val block: String?,
    val branchType: String,
    val circle: String,
    val country: String,
    val deliveryStatus: String,
    val district: String,
    val division: String,
    val name: String,
    val pinCode: String,
    val region: String,
    val state: String
)
