package com.jetpackcopmosedemo.domain.repository.auth

import retrofit2.Response


interface AuthRepository {
    suspend fun signIn(loginCreds: Map<String, String>): Response<Map<String, String>>
}
