package com.plcoding.weatherapp.data.observer

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.plcoding.weatherapp.domain.data.InternetStatus
import com.plcoding.weatherapp.domain.observer.InternetConnectionObserver
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class InternetConnectionObserverImpl @Inject constructor(
    private val application: Application
) : InternetConnectionObserver {

    private val connectionManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<InternetStatus> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(InternetStatus.Available) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(InternetStatus.UnAvailable) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(InternetStatus.UnAvailable) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(InternetStatus.UnAvailable) }
                }
            }

            connectionManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectionManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}