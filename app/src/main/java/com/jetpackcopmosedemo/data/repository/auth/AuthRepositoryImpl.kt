package com.jetpackcopmosedemo.data.repository.auth

import com.jetpackcopmosedemo.data.datasource.ApiService
import com.jetpackcopmosedemo.domain.repository.auth.AuthRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {
    override suspend fun signIn(loginCreds: Map<String, String>): Response<Map<String, String>> {
        return apiService.signIn(loginCreds)
    }
}
