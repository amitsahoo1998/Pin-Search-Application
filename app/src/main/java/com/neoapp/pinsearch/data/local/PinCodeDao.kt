package com.neoapp.pinsearch.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PinCodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPinCodeData(pinData : List<PinCodeEntity>)
    @Query("SELECT * FROM PinTable")
    fun getAllPinData() : Flow<List<PinCodeEntity>>
    @Query("DELETE FROM PinTable")
    suspend fun deleteAllData()
}