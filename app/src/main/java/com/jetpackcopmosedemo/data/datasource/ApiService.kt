package com.jetpackcopmosedemo.data.datasource

import okhttp3.RequestBody
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiService {
    @GET("login")
    fun signIn(@Body loginCreds: RequestBody): Response
}