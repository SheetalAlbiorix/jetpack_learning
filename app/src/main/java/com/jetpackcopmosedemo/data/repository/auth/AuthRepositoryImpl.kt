package com.jetpackcopmosedemo.data.repository.auth

import com.jetpackcopmosedemo.data.datasource.ApiService
import com.jetpackcopmosedemo.domain.repository.auth.AuthRepository
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {
    override suspend fun signIn(loginCreds: Map<String, String>): ResponseBody {
        return apiService.signIn(loginCreds)
    }
}
