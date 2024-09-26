package com.jetpackcopmosedemo.domain.repository.event

import retrofit2.Response


interface DashboardRepository {
    suspend fun getEvent(): Response<Map<String, List<String>>>
    suspend fun editEvent(eventName: String): Response<Map<String, String>>
    suspend fun deleteEvent(eventId: Int): Response<Map<String, List<String>>>
}
