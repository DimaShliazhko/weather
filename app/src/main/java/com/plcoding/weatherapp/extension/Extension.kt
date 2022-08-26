package com.plcoding.weatherapp.extension

import com.plcoding.weatherapp.data.room.MapsPoint
import com.plcoding.weatherapp.data.room.MapsPointEntity

fun MapsPointEntity.toMapsPoint(): MapsPoint {
    return MapsPoint(
        lat = this.lat,
        lng = this.lng,
        id = this.id,
    )
}

fun MapsPoint.toMapsPointEntity(): MapsPointEntity {
    return MapsPointEntity(
        lat = this.lat,
        lng = this.lng,
        id = this.id,
    )
}