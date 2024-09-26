package com.jetpackcopmosedemo.data.datasource

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun signIn(@Body loginCreds: Map<String, String>): Response<Map<String, String>>

    @GET("getEvents")
    suspend fun getEvents(): Response<Map<String, List<String>>>

    @PATCH("editEvents")
    suspend fun editEvents(@Body eventName: String): Response<Map<String, String>>

    @DELETE("deleteEvents")
    suspend fun deleteEvents(): Response<Map<String, List<String>>>
}