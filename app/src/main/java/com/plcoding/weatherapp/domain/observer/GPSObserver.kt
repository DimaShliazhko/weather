package com.plcoding.weatherapp.domain.observer

import com.plcoding.weatherapp.domain.data.GPSStatus
import com.plcoding.weatherapp.domain.data.InternetStatus
import kotlinx.coroutines.flow.Flow

interface GPSObserver {

    fun observe(): Flow<GPSStatus>
}