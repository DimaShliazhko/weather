package com.plcoding.weatherapp.presentation.ui.map

import com.google.maps.android.compose.MapProperties
import com.plcoding.weatherapp.data.room.MapsPoint
import com.plcoding.weatherapp.presentation.base.State

data class MapState(
    val isLoading: Boolean = false,
    val isFalloutMap: Boolean = false,
    val mapProperties: MapProperties = MapProperties(isMyLocationEnabled = true),
    val mapPoints: List<MapsPoint> = emptyList()
) : State