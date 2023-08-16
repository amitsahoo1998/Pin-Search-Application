package com.neoapp.pinsearch.presenter.di

import android.app.Application
import com.neoapp.pinsearch.data.local.PinDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) = PinDataBase.getInstance(application)

    @Singleton
    @Provides
    fun provideCarDao(database: PinDataBase) = database.getPinDao()
}