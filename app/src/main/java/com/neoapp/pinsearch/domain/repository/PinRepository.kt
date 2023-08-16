package com.neoapp.pinsearch.domain.repository

import com.neoapp.pinsearch.data.local.PinCodeEntity
import com.neoapp.pinsearch.data.model.PostOfficeResponse
import com.neoapp.pinsearch.domain.Either
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface PinRepository {

    suspend fun getPinData(pin : String) : Either<PostOfficeResponse>

    suspend fun getPostOfficeData(postOffice : String) : Either<PostOfficeResponse>

    fun getAllPinDataHistory() : Flow<List<PinCodeEntity>>

    suspend fun deleteAllPinDataHistory()
}