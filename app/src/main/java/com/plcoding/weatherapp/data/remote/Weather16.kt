package com.plcoding.weatherapp.data.remote

import androidx.annotation.DrawableRes
import com.plcoding.weatherapp.R
import com.plcoding.weatherapp.domain.weather.WeatherType

sealed class Weather16(
    val description: String,
    @DrawableRes val iconRes: Int
) {
    object ClearSky : Weather16(
        description = "Clear sky",
        iconRes = R.drawable.ic_sunny
    )

    object ThunderstormLightRain : Weather16(
        description = "Thunderstorm with light rain",
        iconRes = R.drawable.ic_rainythunder
    )

    object ThunderstormRain : Weather16(
        description = "Thunderstorm with rain",
        iconRes = R.drawable.ic_rainythunder
    )

    object ThunderstormHeavyRain : Weather16(
        description = "Thunderstorm with heavy rain",
        iconRes = R.drawable.ic_rainythunder
    )

    object ThunderstormLightDrizzle : Weather16(
        description = "Thunderstorm with light drizzle",
        iconRes = R.drawable.ic_thunder
    )

    object ThunderstormDrizzle : Weather16(
        description = "Thunderstorm with drizzle",
        iconRes = R.drawable.ic_thunder
    )

    object ThunderstormHeavyDrizzle : Weather16(
        description = "Thunderstorm with heavy drizzle",
        iconRes = R.drawable.ic_thunder
    )

    object ThunderstormHail : Weather16(
        description = "Thunderstorm with Hail",
        iconRes = R.drawable.ic_thunder
    )

    object LightDrizzle : Weather16(
        description = "Light Drizzle",
        iconRes = R.drawable.ic_rainy
    )

    object Drizzle : Weather16(
        description = "Drizzle",
        iconRes = R.drawable.ic_rainy
    )

    object HeavyDrizzle : Weather16(
        description = "Heavy Drizzle",
        iconRes = R.drawable.ic_rainy
    )

    object LightRain : Weather16(
        description = "Light Rain",
        iconRes = R.drawable.ic_rainy
    )

    object ModerateRain : Weather16(
        description = "Moderate Rain",
        iconRes = R.drawable.ic_rainy
    )

    object HeavyRain : Weather16(
        description = "Heavy Rain",
        iconRes = R.drawable.ic_rainy
    )

    object FreezingRain : Weather16(
        description = "Freezing rain",
        iconRes = R.drawable.ic_rainy
    )

    object LightShowerRain : Weather16(
        description = "Light shower rain",
        iconRes = R.drawable.ic_rainy
    )

    object ShowerRain : Weather16(
        description = "Shower rain",
        iconRes = R.drawable.ic_snowyrainy
    )

    object HeavyShowerRain : Weather16(
        description = "Heavy shower rain",
        iconRes = R.drawable.ic_rainy
    )

    object LightSnow : Weather16(
        description = "Light snow",
        iconRes = R.drawable.ic_snowy
    )

    object Snow : Weather16(
        description = "Snow",
        iconRes = R.drawable.ic_snowy
    )

    object HeavySnow : Weather16(
        description = "Heavy Snow",
        iconRes = R.drawable.ic_snowy
    )

    object MixSnowRain : Weather16(
        description = "Mix snow/rain",
        iconRes = R.drawable.ic_snowyrainy
    )

    object Sleet : Weather16(
        description = "Sleet",
        iconRes = R.drawable.ic_windy
    )

    object HeavySleet : Weather16(
        description = "Heavy sleet",
        iconRes = R.drawable.ic_windy
    )

    object SnowShower : Weather16(
        description = "Snow shower",
        iconRes = R.drawable.ic_sunnyrainy
    )

    object HeavySnowShower : Weather16(
        description = "Heavy snow shower",
        iconRes = R.drawable.ic_snowy
    )

    object Flurries : Weather16(
        description = "Flurries",
        iconRes = R.drawable.ic_windy
    )

    object Mist : Weather16(
        description = "Mist",
        iconRes = R.drawable.ic_cloudy
    )

    object Smoke : Weather16(
        description = "Smoke",
        iconRes = R.drawable.ic_cloudy
    )

    object Haze : Weather16(
        description = "Haze",
        iconRes = R.drawable.ic_cloudy
    )

    object SandDust : Weather16(
        description = "Sand/dust",
        iconRes = R.drawable.ic_cloudy
    )

    object Fog : Weather16(
        description = "Fog",
        iconRes = R.drawable.ic_cloudy
    )

    object FreezingFog : Weather16(
        description = "Freezing Fog",
        iconRes = R.drawable.ic_cloudy
    )

    object FewClouds : Weather16(
        description = "Few clouds",
        iconRes = R.drawable.ic_sunnycloudy
    )

    object ScatteredClouds : Weather16(
        description = "Scattered clouds",
        iconRes = R.drawable.ic_sunnycloudy
    )

    object BrokenClouds : Weather16(
        description = "Broken clouds",
        iconRes = R.drawable.ic_sunny
    )

    object OvercastClouds : Weather16(
        description = "Overcast clouds",
        iconRes = R.drawable.ic_sunny
    )

    object UnknownPrecipitation : Weather16(
        description = "Unknown Precipitation",
        iconRes = R.drawable.ic_sunny
    )


    companion object {
        fun fromWMO(code: Int): Weather16 {
            return when (code) {
                200 -> Weather16.ThunderstormLightRain
                201 -> Weather16.ThunderstormRain
                202 -> Weather16.ThunderstormHeavyRain
                230 -> Weather16.ThunderstormLightDrizzle
                231 -> Weather16.ThunderstormDrizzle
                232 -> Weather16.ThunderstormHeavyDrizzle
                233 -> Weather16.ThunderstormHail
                300 -> Weather16.LightDrizzle
                301 -> Weather16.Drizzle
                302 -> Weather16.HeavyDrizzle
                500 -> Weather16.LightRain
                501 -> Weather16.ModerateRain
                502 -> Weather16.HeavyRain
                511 -> Weather16.FreezingRain
                520 -> Weather16.LightShowerRain
                521 -> Weather16.ShowerRain
                522 -> Weather16.HeavyShowerRain
                600 -> Weather16.LightSnow
                601 -> Weather16.Snow
                602 -> Weather16.HeavySnow
                610 -> Weather16.MixSnowRain
                611 -> Weather16.Sleet
                612 -> Weather16.HeavySleet
                621 -> Weather16.SnowShower
                622 -> Weather16.HeavySnowShower
                623 -> Weather16.Flurries
                700 -> Weather16.Mist
                711 -> Weather16.Smoke
                721 -> Weather16.Haze
                731 -> Weather16.SandDust
                741 -> Weather16.Fog
                751 -> Weather16.FreezingFog
                800 -> Weather16.ClearSky
                801 -> Weather16.FewClouds
                802 -> Weather16.ScatteredClouds
                803 -> Weather16.BrokenClouds
                804 -> Weather16.OvercastClouds
                900 -> Weather16.UnknownPrecipitation
                else -> Weather16.ClearSky
            }
        }
    }
}