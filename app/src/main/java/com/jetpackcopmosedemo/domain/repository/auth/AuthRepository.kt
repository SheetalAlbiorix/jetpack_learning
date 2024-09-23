package com.jetpackcopmosedemo.domain.repository.auth

import okhttp3.RequestBody
import okhttp3.Response

interface AuthRepository {
    fun signIn(loginCreds: RequestBody): Response
}
