package com.jetpackcopmosedemo.data.network.client

import android.util.Log
import com.google.gson.Gson
import com.jetpackcopmosedemo.data.network.model.ApiModel
import com.jetpackcopmosedemo.utils.getRequestBodyAsJson
import com.jetpackcopmosedemo.utils.mock.endPointNotFoundResponse
import com.jetpackcopmosedemo.utils.mock.loginCredsRequest
import com.jetpackcopmosedemo.utils.mock.methodNotSupportedResponse
import com.jetpackcopmosedemo.utils.mock.mockApisResponses
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody


class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request: Request = chain.request()
            val url = mockApisResponses[request.url.toString()] as ApiModel?
            val gson = Gson()

            // Check if the request is to the specific URL
            if (url != null) {
                if (request.method == url.method) {
                    // Create a predefined response for the specific URL
                    if (request.url.toString()
                            .contains("login") && request.getRequestBodyAsJson() == loginCredsRequest
                    ) {
                        val response = Response.Builder()
                            .request(request)
                            .protocol(Protocol.HTTP_1_1)
                            .code(200)
                            .message("Ok")
                            .body(
                                gson.toJson(url.response.success)
                                    .toResponseBody("application/json".toMediaType())
                            )
                            .build()
                        return response
                    } else if (!request.url.toString().contains("login")) {
                        val response = Response.Builder()
                            .request(request)
                            .protocol(Protocol.HTTP_1_1)
                            .code(200)
                            .message("Ok")
                            .body(
                                gson.toJson(url.response.success)
                                    .toResponseBody("application/json".toMediaType())
                            )
                            .build()
                        return response
                    } else {
                        return Response.Builder()
                            .request(request)
                            .protocol(Protocol.HTTP_1_1)
                            .code(403)
                            .message("Error")
                            .body(
                                gson.toJson(url.response.error)
                                    .toResponseBody("application/json".toMediaType())
                            )
                            .build()
                    }
                } else {
                    return Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_1_1)
                        .code(405)
                        .message("ERROR")
                        .body(
                            gson.toJson(methodNotSupportedResponse)
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
                        endPointNotFoundResponse.toString()
                            .toResponseBody("application/json".toMediaType())
                    )
                    .build()
            }
        } catch (e: Exception) {
            Log.e("TAG", "interceptor ERROR: ${e.message}")
            return Response.Builder()
                .code(500)
                .body(e.message?.toResponseBody("application/json".toMediaType())).build()
        }
    }
}

/*
fun getRetrofitInstance(): Retrofit {
    // Create an OkHttpClient and attach the custom MockInterceptor
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(MockInterceptor())
        .build()
    return Retrofit.Builder()
        .baseUrl("https://example.com/api/") // Base URL
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}*/
