package com.neoapp.pinsearch.presenter.view

import androidx.lifecycle.viewModelScope
import com.neoapp.pinsearch.domain.repository.PinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinHistoryViewModel @Inject constructor(private val pinRepository: PinRepository) : BaseViewModel<HistoryState>(initialState = HistoryState())  {

    init {
        observePinData()
    }

    private fun observePinData() {
        pinRepository.getAllPinDataHistory()
            .distinctUntilChanged()
            .onEach {
                setState { state ->
                    state.copy(
                        isLoading = false,
                        pinHistory = it
                    )
                }
            }.onStart {
                setState { state -> state.copy(isLoading = true) }
            }.launchIn(viewModelScope)
    }

    fun deleteAllHistoryData(){
        viewModelScope.launch {
            pinRepository.deleteAllPinDataHistory()
        }
    }
}