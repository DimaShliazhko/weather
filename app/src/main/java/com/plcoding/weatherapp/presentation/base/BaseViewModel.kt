package com.plcoding.weatherapp.presentation.base

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiEvent : Event, UiState : State, UiAction : Action> : ViewModel() {

    private val initialState: UiState by lazy { createInitialState() }
    abstract fun createInitialState(): UiState

    var state = mutableStateOf(initialState)

    private val _event: MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _action: MutableSharedFlow<UiAction> = MutableSharedFlow()
    val action = _action.asSharedFlow()

    init {
        subscribeEvents()
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    abstract fun handleEvent(event: UiEvent)

    fun setEvent(event: UiEvent) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun setAction(action: UiAction) {
        viewModelScope.launch { _action.emit(action) }
    }

}