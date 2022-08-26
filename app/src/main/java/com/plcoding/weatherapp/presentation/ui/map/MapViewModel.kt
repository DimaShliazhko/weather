package com.plcoding.weatherapp.presentation.ui.map

import androidx.lifecycle.viewModelScope
import com.plcoding.weatherapp.data.room.MapsPoint
import com.plcoding.weatherapp.domain.repository.MapRepository
import com.plcoding.weatherapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: MapRepository
) : BaseViewModel<MapEvents, MapState, MapAction>() {

    init {
        viewModelScope.launch {
            repository.selectAllMapsPoint().collectLatest { points ->
                state.value = state.value.copy(
                    mapPoints = points
                )
            }
        }
    }

    override fun createInitialState(): MapState {
        return MapState()
    }

    override fun handleEvent(event: MapEvents) {
        when (event) {
            is MapEvents.OnInfoWindowLongClick -> {
                viewModelScope.launch {
                    repository.deleteMapsPoint(event.point)
                }
            }
            is MapEvents.OnMapsLongClick -> {
                viewModelScope.launch {
                    repository.insertMapsPoint(
                        MapsPoint(
                            event.latLng.latitude,
                            event.latLng.longitude
                        )
                    )
                }
            }

        }
    }
}