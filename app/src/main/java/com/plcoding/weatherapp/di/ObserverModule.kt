package com.plcoding.weatherapp.di

import com.plcoding.weatherapp.data.observer.GPSConnectionObserverImpl
import com.plcoding.weatherapp.data.observer.InternetConnectionObserverImpl
import com.plcoding.weatherapp.domain.observer.GPSObserver
import com.plcoding.weatherapp.domain.observer.InternetConnectionObserver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ObserverModule {

    @Binds
    @Singleton
    abstract fun bindInternetConnectionObserverImpl(internetConnectionObserverImpl: InternetConnectionObserverImpl):
            InternetConnectionObserver

    @Binds
    @Singleton
    abstract fun bindGPSConnectionObserverImpl(gpsConnectionObserverImpl: GPSConnectionObserverImpl): GPSObserver
}