package com.plcoding.weatherapp.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.plcoding.weatherapp.data.remote.WeatherApi
import com.plcoding.weatherapp.data.repository.MapRepositoryImpl
import com.plcoding.weatherapp.data.room.MapDatabase
import com.plcoding.weatherapp.domain.location.LocationTracker
import com.plcoding.weatherapp.domain.repository.DataStoreRepository
import com.plcoding.weatherapp.domain.repository.MapRepository
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.presentation.ui.weather.WeatherUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

private const val MAP_PREFERENCES = "map_preferences"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun provideMapDatabase(app: Application): MapDatabase {
        return Room.databaseBuilder(
            app,
            MapDatabase::class.java,
            "map_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMapRepositoryImpl(db: MapDatabase): MapRepository {
        return MapRepositoryImpl(db.dao)
    }

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { appContext.preferencesDataStoreFile(MAP_PREFERENCES) }
        )
    }


    @Singleton
    @Provides
    fun provideWeatherUseCase(
        repository: WeatherRepository,
        dataStoreRepository: DataStoreRepository,
        locationTracker: LocationTracker,
    ): WeatherUseCase {
        return WeatherUseCase(repository, dataStoreRepository, locationTracker)
    }


}