package com.jetpackcopmosedemo.domain.repository.event

import retrofit2.Response


interface DashboardRepository {
    suspend fun getEvent(): Response<Map<String, List<String>>>
}
