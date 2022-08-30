package com.plcoding.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.weatherapp.features.NotificationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowViewModel @Inject constructor(

) : ViewModel() {

    @Inject lateinit var notificationUtils: NotificationUtils
    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    private val _shareFlow = MutableSharedFlow<Int>(replay = 5)
    val shareFlow = _shareFlow.asSharedFlow()


    fun incrementCounter() {
        _stateFlow.value += 1
    }

    fun showNotification() {
        notificationUtils.buildForegroundServiceNotification(0)
    }


    fun squarenumber(number: Int) {
        viewModelScope.launch {
            _shareFlow.emit(number * number)
        }
    }


    init {
        squarenumber(3)
        viewModelScope.launch {
            shareFlow.collect {
                delay(2000L)
                println("first Flow: number $it")
            }
        }

        viewModelScope.launch {
            shareFlow.collect {
                delay(3000L)
                println("second Flow: number $it")
            }
        }

    }

}