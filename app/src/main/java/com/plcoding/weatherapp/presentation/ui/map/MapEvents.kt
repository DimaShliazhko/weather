package com.plcoding.weatherapp.presentation.ui.map

import com.google.android.gms.maps.model.LatLng
import com.plcoding.weatherapp.data.room.MapsPoint
import com.plcoding.weatherapp.presentation.base.Event

sealed class MapEvents() : Event {
    data class OnMapsLongClick(val latLng: LatLng) : MapEvents()
    data class OnInfoWindowLongClick(val point: MapsPoint ) : MapEvents()
}