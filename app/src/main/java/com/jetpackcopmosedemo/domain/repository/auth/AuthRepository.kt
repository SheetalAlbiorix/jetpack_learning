package com.jetpackcopmosedemo.domain.repository.auth

import okhttp3.ResponseBody

interface AuthRepository {
    suspend fun signIn(loginCreds: Map<String, String>): ResponseBody
}
