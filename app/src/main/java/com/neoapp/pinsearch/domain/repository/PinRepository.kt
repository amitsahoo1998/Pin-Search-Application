package com.neoapp.pinsearch.domain.repository

import com.neoapp.pinsearch.data.model.PostOfficeResponse
import com.neoapp.pinsearch.domain.Either
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface PinRepository {

    suspend fun getPinData(pin : String) : Either<PostOfficeResponse>

    suspend fun getPostOfficeData(postOffice : String) : Either<PostOfficeResponse>

}