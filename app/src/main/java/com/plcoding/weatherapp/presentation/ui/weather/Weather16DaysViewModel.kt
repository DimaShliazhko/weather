package com.plcoding.weatherapp.presentation.ui.weather

import androidx.lifecycle.viewModelScope
import com.plcoding.weatherapp.domain.data.InternetStatus
import com.plcoding.weatherapp.domain.observer.InternetConnectionObserver
import com.plcoding.weatherapp.domain.util.Results
import com.plcoding.weatherapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Weather16DaysViewModel @Inject constructor(
    private val getWeatherUseCase: Weather16UseCase,
    private val internetObserver: InternetConnectionObserver,
) : BaseViewModel<WeatherEvents, Weather16DaysState, WeatherAction>() {

    init {
        loadWeather()
    }

    private fun loadWeather() {
        state.value = state.value.copy(
            isLoading = true,
            error = null
        )

        viewModelScope.launch {
            internetObserver.observe().collect { status ->
                when (status) {
                    is InternetStatus.Available -> {
                        state.value = state.value.copy(
                            isHasInternetConnection = true
                        )
                        loadWeatherInfo()
                    }
                    else -> {
                        state.value = state.value.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = null,
                            isHasInternetConnection = false
                        )
                    }
                }
            }
        }
    }

    private fun loadWeatherInfo() {
        viewModelScope.launch {

            when (val result = getWeatherUseCase()) {
                is Results.Success -> {
                    state.value = state.value.copy(
                        weatherInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Results.Error -> {
                    state.value = state.value.copy(
                        weatherInfo = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }

    }


    override fun createInitialState(): Weather16DaysState {
        return Weather16DaysState()
    }

    override fun handleEvent(event: WeatherEvents) {
        TODO("Not yet implemented")
    }
}