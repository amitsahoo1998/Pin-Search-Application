package com.neoapp.pinsearch.presenter.view

import com.neoapp.pinsearch.data.local.PinCodeEntity

data class HistoryState(
    val isLoading : Boolean = false,
    val pinHistory : List<PinCodeEntity> = emptyList()
) : State
