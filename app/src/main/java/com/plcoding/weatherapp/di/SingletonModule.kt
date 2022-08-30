package com.plcoding.weatherapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object SingletonModule {

    @Provides
    fun providesContext(application: Application): Context {
        return application.applicationContext
    }
}