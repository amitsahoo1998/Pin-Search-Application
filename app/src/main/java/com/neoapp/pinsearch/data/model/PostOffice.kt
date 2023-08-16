package com.neoapp.pinsearch.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostOffice(
    @Json(name = "Block")
    val block: String?,
    @Json(name = "BranchType")
    val branchType: String,
    @Json(name = "Circle")
    val circle: String,
    @Json(name = "Country")
    val country: String,
    @Json(name = "DeliveryStatus")
    val deliveryStatus: String,
    @Json(name = "District")
    val district: String,
    @Json(name = "Division")
    val division: String,
    @Json(name = "Name")
    val name: String,
    @Json(name = "Pincode")
    val pinCode: String,
    @Json(name = "Region")
    val region: String,
    @Json(name = "State")
    val state: String
)