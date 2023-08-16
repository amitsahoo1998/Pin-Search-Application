package com.neoapp.pinsearch.presenter.view

import androidx.lifecycle.viewModelScope
import com.neoapp.pinsearch.data.model.PostOffice
import com.neoapp.pinsearch.domain.repository.PinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinViewModel @Inject constructor(
    private val pinRepository: PinRepository
) : BaseViewModel<PinSearchScreenState>(initialState = PinSearchScreenState()) {

    fun pinSearch() {
        viewModelScope.launch {
            setState { state -> state.copy(isLoading = true) }

            val response = pinRepository.getPinData(currentState.pinCode)

            response.onSuccess { postOfficeResponse ->
                setState { state ->
                    state.copy(
                        isLoading = false,
                        postOfficeResponse = postOfficeResponse.postOffice ?: emptyList()
                    )
                }
            }.onError {
                setState { state ->
                    state.copy(
                        isLoading = false,
                        apiError = it
                    )
                }
            }
        }
    }

    fun postOfficeSearch() {
        viewModelScope.launch {
            setState { state -> state.copy(isLoading = true) }

            val response = pinRepository.getPostOfficeData(currentState.postOffice)

            response.onSuccess { postOfficeResponse ->
                setState { state ->
                    state.copy(
                        isLoading = false,
                        postOfficeResponse = postOfficeResponse.postOffice ?: emptyList()
                    )
                }
            }.onError {
                setState { state ->
                    state.copy(
                        isLoading = false,
                        apiError = it
                    )
                }
            }
        }
    }

    fun clearError() = setState { state -> state.copy(apiError = null) }

    fun setPinCode(pinCode: String) {
        if (pinCode.length <= 6) {
            setState { state ->
                state.copy(
                    pinCode = pinCode
                )
            }
        }
    }

    fun setPostOffice(postOffice: String) {
        setState { state ->
            state.copy(
                postOffice = postOffice.trim()
            )
        }
    }
}