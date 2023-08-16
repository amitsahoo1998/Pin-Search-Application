package com.neoapp.pinsearch.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostOfficeResponse(
    @Json(name = "Message")
    val message: String,
    @Json(name = "PostOffice")
    val postOffice: List<PostOffice>?,
    @Json(name = "Status")
    val status: String
)