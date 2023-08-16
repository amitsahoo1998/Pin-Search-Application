package com.neoapp.pinsearch.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neoapp.pinsearch.presenter.view.BaseViewModel
import com.neoapp.pinsearch.presenter.view.State

@Composable
fun <S : State, VM : BaseViewModel<S>> VM.collectState() = state.collectAsStateWithLifecycle()