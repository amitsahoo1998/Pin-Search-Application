package com.neoapp.pinsearch.presenter.di

import com.airbnb.lottie.BuildConfig
import com.neoapp.pinsearch.data.remote.PinApiService
import com.neoapp.pinsearch.data.repository.PinRepositoryImpl
import com.neoapp.pinsearch.domain.repository.PinRepository
import com.neoapp.pinsearch.utils.Constant
import com.neoapp.pinsearch.utils.moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private val baseRetrofitBuilder : Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constant.PIN_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))

    private val okHttpClientBuilder : OkHttpClient.Builder =
        OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val level = if (true) {
            HttpLoggingInterceptor.Level.BODY
        } else HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().also {
            it.level = level
        }
    }
    @Provides
    fun providePinApiService(loggingInterceptor: HttpLoggingInterceptor) : PinApiService {
        return baseRetrofitBuilder
            .client(okHttpClientBuilder.addInterceptor(loggingInterceptor).build())
            .build()
            .create(PinApiService::class.java)
    }

    @Provides
    fun providePinRepository(pinApiService: PinApiService) : PinRepository{
        return PinRepositoryImpl(pinApiService)
    }
}