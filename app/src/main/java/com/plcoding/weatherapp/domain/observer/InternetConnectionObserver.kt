package com.plcoding.weatherapp.domain.observer

import com.plcoding.weatherapp.domain.data.InternetStatus
import kotlinx.coroutines.flow.Flow

interface InternetConnectionObserver {

    fun observe(): Flow<InternetStatus>
}