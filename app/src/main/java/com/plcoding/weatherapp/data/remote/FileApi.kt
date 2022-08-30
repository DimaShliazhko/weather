package com.plcoding.weatherapp.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface FileApi {

    @GET("/slovarik/android/14-07-2018_07-39-07.jpg")
    suspend fun downloadFile(): Response<ResponseBody>
}