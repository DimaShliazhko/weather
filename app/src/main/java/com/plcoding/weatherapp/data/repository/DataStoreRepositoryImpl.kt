package com.plcoding.weatherapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import com.plcoding.weatherapp.data.room.MapsPoint
import com.plcoding.weatherapp.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {
    override suspend fun saveMapPoint(value: MapsPoint) {
        dataStore.edit { pref ->
            pref[DataStoreKey.FIELD_LNG] = value.lng
            pref[DataStoreKey.FIELD_LAT] = value.lat
        }
    }

    override suspend fun readMapPoint(): MapsPoint? {
        val preferences = dataStore.data.first()
        val lng = preferences[DataStoreKey.FIELD_LNG]
        val lat = preferences[DataStoreKey.FIELD_LAT]
        return if (lat != null && lng != null) {
            MapsPoint(lat = lat, lng = lng)
        } else {
            null
        }
    }
}

object DataStoreKey {
    val FIELD_LAT = doublePreferencesKey("lat")
    val FIELD_LNG = doublePreferencesKey("lng")
}