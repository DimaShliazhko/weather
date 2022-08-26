package com.plcoding.weatherapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MapsPointEntity::class],
    version = 1
)
abstract class MapDatabase() : RoomDatabase() {
    abstract val dao: MapDAO
}