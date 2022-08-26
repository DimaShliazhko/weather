package com.plcoding.weatherapp.data.observer

import android.app.Application
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import com.plcoding.weatherapp.domain.data.GPSStatus
import com.plcoding.weatherapp.domain.observer.GPSObserver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GPSConnectionObserverImpl @Inject constructor(
    private val application: Application
) : GPSObserver {

    private val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    override fun observe(): Flow<GPSStatus> {
      return flowOf(GPSStatus.UnAvailable)
        }
    }