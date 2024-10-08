package com.jetpackcopmosedemo.data.repository.event

import com.jetpackcopmosedemo.data.datasource.ApiService
import com.jetpackcopmosedemo.domain.repository.event.DashboardRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : DashboardRepository {
    override suspend fun getEvent(): Response<Map<String, List<String>>> {
        return apiService.getEvents()
    }

    override suspend fun editEvent(
        eventId: Int,
        eventName: Map<String, String>
    ): Response<Map<String, List<String>>> {
        return apiService.editEvents(eventId, eventName)
    }

    override suspend fun deleteEvent(eventId: Int): Response<Map<String, List<String>>> {
        return apiService.deleteEvents(eventId)
    }
}
