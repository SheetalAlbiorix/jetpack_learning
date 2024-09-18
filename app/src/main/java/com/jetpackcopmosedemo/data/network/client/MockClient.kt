package com.jetpackcopmosedemo.data.network.client

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jetpackcopmosedemo.utils.mock.listResponse
import com.jetpackcopmosedemo.utils.mock.loginCredsRequest
import com.jetpackcopmosedemo.utils.mock.methodNotSupportedResponse
import com.jetpackcopmosedemo.utils.mock.unauthenticatedResponse
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException


class MockInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val url = request.url.toString()
        val methodNotSupportedResponse = methodNotSupportedResponse.toString()

        // Check if the request is to the specific URL
        if (request.method == "POST" && url == "https://example.com/api/login") {
            // Create a predefined response for the specific URL
            val mockResponse = listResponse.toString()
            Log.e("Login", "intercept: ${request.body.toString()}")
            val buffer = okio.Buffer()
            request.body?.writeTo(buffer)
            val type = object : TypeToken<Map<Any, Any>>() {}.type
            val finalRequest = buffer.readUtf8()
            val jsonRequest = Gson().fromJson<Map<Any, Any>>(finalRequest, type)
            // Return the predefined response
//            jsonRequest["email"] == "khushpajwani2000@gmail.com" && jsonRequest["password"] == "khushpajwani2000@gmail.com"
            if (jsonRequest == loginCredsRequest)
                return Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(200)
                    .message("Error")
                    .body(
                        mockResponse.toResponseBody("application/json".toMediaType())
                    )
                    .build()
            else {
                val jsonString =
                    unauthenticatedResponse.entries.joinToString(prefix = "{", postfix = "}") {
                        "\"${it.key}\": \"${it.value}\""
                    }
                val message = Gson().fromJson<Map<Any, Any>>(jsonString, type)["message"]
                return Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(401)
                    .message("Error")
                    .body(
                        message.toString()
                            .toResponseBody("application/json".toMediaType())
                    )
                    .build()
            }
        } else {
            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(404)
                .message("ERROR")
                .body(
                    methodNotSupportedResponse.toResponseBody("application/json".toMediaType())
                )
                .build()
        }
    }
}

fun getRetrofitInstance(): OkHttpClient {
    // Create an OkHttpClient and attach the custom MockInterceptor

    return OkHttpClient.Builder()
        .addInterceptor(MockInterceptor())
        .build()
}