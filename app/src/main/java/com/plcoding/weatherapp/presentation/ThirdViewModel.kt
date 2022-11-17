package com.plcoding.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdViewModel @Inject constructor() : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    private val _channel = Channel<Int>()
    val channel = _channel.receiveAsFlow()

    init {
        viewModelScope.launch {
            repeat(100) {
                _sharedFlow.emit(it)
                delay(1000L)
            }
        }

        viewModelScope.launch {
            repeat(100) {
                _channel.send(it)
                delay(1000L)
            }
        }
    }
}