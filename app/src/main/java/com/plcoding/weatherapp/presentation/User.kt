package com.plcoding.weatherapp.presentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(
    val firstName: String,
    val secondName: String,
    val age: Int
) : Parcelable