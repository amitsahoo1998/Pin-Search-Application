package com.neoapp.pinsearch.data.repository

import com.neoapp.pinsearch.data.local.PinCodeDao
import com.neoapp.pinsearch.data.local.PinCodeEntity
import com.neoapp.pinsearch.data.local.toPinCodeEntity
import com.neoapp.pinsearch.data.model.PostOfficeResponse
import com.neoapp.pinsearch.data.remote.PinApiService
import com.neoapp.pinsearch.domain.Either
import com.neoapp.pinsearch.domain.repository.PinRepository
import com.neoapp.pinsearch.utils.getResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PinRepositoryImpl @Inject constructor(private val pinApiService: PinApiService ,private val pinDao : PinCodeDao) :
    PinRepository {

    override suspend fun getPinData(pin: String): Either<PostOfficeResponse> {
        return runCatching {
            val pinCodeResponse = pinApiService.getPinData(pin).getResponse()[0]

            when (pinCodeResponse.status) {
                "Success" -> {
                    pinCodeResponse.postOffice?.let { pinDao.addPinCodeData(it.map { it.toPinCodeEntity() }) }
                    Either.success(pinCodeResponse)
                }
                "Error" -> Either.error(pinCodeResponse.message)
                else -> Either.error(pinCodeResponse.message)
            }

        }.getOrElse {
            Either.error(it.toString())
        }
    }

    override suspend fun getPostOfficeData(postOffice: String): Either<PostOfficeResponse> {
        return runCatching {
            val postOfficeResponse = pinApiService.getPostOfficePin(postOffice).getResponse()[0]

            when(postOfficeResponse.status){
                "Success" -> {
                    postOfficeResponse.postOffice?.let { pinDao.addPinCodeData(it.map { it.toPinCodeEntity() }) }
                    Either.success(postOfficeResponse)
                }
                "Error" -> Either.error(postOfficeResponse.message)
                else -> Either.error(postOfficeResponse.message)
            }

        }.getOrElse {
            Either.error(it.toString())
        }
    }

    override fun getAllPinDataHistory(): Flow<List<PinCodeEntity>> = pinDao.getAllPinData()
    override suspend fun deleteAllPinDataHistory()  = pinDao.deleteAllData()

}