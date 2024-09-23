package com.jetpackcopmosedemo.data.repository.auth

import com.jetpackcopmosedemo.data.datasource.ApiService
import com.jetpackcopmosedemo.domain.repository.auth.AuthRepository
import okhttp3.RequestBody
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {
    override fun signIn(loginCreds: RequestBody): Response {
        return apiService.signIn(loginCreds)
    }
}
