package com.jetpackcopmosedemo.data.datasource

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    suspend fun signIn(@Body loginCreds: Map<String, String>): Response<Map<String, String>>

    @GET("getEvents")
    suspend fun getEvents(): Response<Map<String, List<String>>>

    @PATCH("editEvents/{eventId}")
    suspend fun editEvents(
        @Path("eventId") eventId: Int,
        @Body eventName: Map<String, String>
    ): Response<Map<String, List<String>>>

    @DELETE("deleteEvents/{eventId}")
    suspend fun deleteEvents(@Path("eventId") eventId: Int): Response<Map<String, List<String>>>
}