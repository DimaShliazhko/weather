package com.plcoding.weatherapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MapsPointEntity(
    val lat: Double,
    val lng: Double,
    @PrimaryKey
    val id: Int? = null
)