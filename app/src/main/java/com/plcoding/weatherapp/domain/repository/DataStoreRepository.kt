package com.plcoding.weatherapp.domain.repository

import com.plcoding.weatherapp.data.room.MapsPoint
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun saveMapPoint(value: MapsPoint)
    suspend fun readMapPoint(): MapsPoint?
}