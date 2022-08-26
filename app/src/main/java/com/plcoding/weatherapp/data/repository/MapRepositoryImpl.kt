package com.plcoding.weatherapp.data.repository

import com.plcoding.weatherapp.data.room.MapDAO
import com.plcoding.weatherapp.data.room.MapsPoint
import com.plcoding.weatherapp.domain.repository.MapRepository
import com.plcoding.weatherapp.extension.toMapsPoint
import com.plcoding.weatherapp.extension.toMapsPointEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val dao: MapDAO,
) : MapRepository {

    override suspend fun insertMapsPoint(point: MapsPoint) {
        dao.insertMapsPoint(point.toMapsPointEntity())
    }

    override suspend fun deleteMapsPoint(point: MapsPoint) {
        dao.deleteMapsPoint(point.toMapsPointEntity())
    }

    override fun selectAllMapsPoint(): Flow<List<MapsPoint>> {
        return dao.selectAllPoint().map { list ->
            list.map { entity ->
                entity.toMapsPoint()
            }
        }
    }

    override suspend fun saveLastPosition(point: MapsPoint) {
        dao.insertLastPoint(point.toMapsPointEntity())
    }
}