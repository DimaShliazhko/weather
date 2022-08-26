package com.plcoding.weatherapp.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MapDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMapsPoint(point: MapsPointEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLastPoint(point: MapsPointEntity)

    @Query("SELECT * FROM mapsPointEntity")
    fun selectAllPoint(): Flow<List<MapsPointEntity>>

    @Delete
    fun deleteMapsPoint(point: MapsPointEntity)

}