package com.neoapp.pinsearch.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PinCodeEntity::class] , version = 1,
    exportSchema = false)
abstract class PinDataBase : RoomDatabase(){
    abstract fun getPinDao(): PinCodeDao

    companion object {
        private const val DB_NAME = "pin_database"

        @Volatile
        private var INSTANCE: PinDataBase? = null

        fun getInstance(context: Context): PinDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PinDataBase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}