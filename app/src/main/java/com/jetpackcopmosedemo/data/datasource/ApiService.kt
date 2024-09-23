package com.jetpackcopmosedemo.data.datasource

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun signIn(@Body loginCreds: Map<String, String>): ResponseBody
}