package com.plcoding.weatherapp.domain.repository

import com.plcoding.weatherapp.data.room.MapsPoint
import kotlinx.coroutines.flow.Flow

interface MapRepository {

    suspend fun insertMapsPoint(point: MapsPoint)
    suspend fun deleteMapsPoint(point: MapsPoint)
    fun selectAllMapsPoint(): Flow<List<MapsPoint>>
    suspend fun saveLastPosition(point: MapsPoint)
}