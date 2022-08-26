package com.plcoding.weatherapp.domain.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

sealed class GPSStatus() {
    object Available : GPSStatus(), Flow<GPSStatus> {
        override suspend fun collect(collector: FlowCollector<GPSStatus>) {
            TODO("Not yet implemented")
        }
    }

    object UnAvailable : GPSStatus()
}