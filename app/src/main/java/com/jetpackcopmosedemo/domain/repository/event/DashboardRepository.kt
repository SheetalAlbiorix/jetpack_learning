package com.jetpackcopmosedemo.domain.repository.event

import retrofit2.Response


interface DashboardRepository {
    suspend fun getEvent(): Response<Map<String, List<String>>>
    suspend fun editEvent(
        eventId: Int,
        eventName: Map<String, String>
    ): Response<Map<String, List<String>>>
    suspend fun deleteEvent(eventId: Int): Response<Map<String, List<String>>>
}
