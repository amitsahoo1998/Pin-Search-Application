package com.neoapp.pinsearch.presenter.view

import com.neoapp.pinsearch.data.model.PostOffice

data class PinSearchScreenState(
    val isLoading : Boolean = false,
    val pinCode : String = "",
    val postOffice : String = "",
    val postOfficeResponse : List<PostOffice> = emptyList(),
    val apiError : String? = null
) : State
