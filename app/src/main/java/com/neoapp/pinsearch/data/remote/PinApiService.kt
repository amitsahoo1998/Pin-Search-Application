package com.neoapp.pinsearch.data.remote

import com.neoapp.pinsearch.data.model.PostOfficeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path

interface PinApiService {

    @GET("pincode/{pin}")
    suspend fun getPinData(
        @Path("pin") pin : String
    ) : Response<List<PostOfficeResponse>>

    @GET("postoffice/{post_office}")
    suspend fun getPostOfficePin(
        @Path("post_office") postOffice : String
    ) : Response<List<PostOfficeResponse>>

}