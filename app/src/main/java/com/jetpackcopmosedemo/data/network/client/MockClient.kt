package com.jetpackcopmosedemo.data.network.client

//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import com.jetpackcopmosedemo.data.network.model.ApiModel
import com.jetpackcopmosedemo.utils.getMessageFromMap
import com.jetpackcopmosedemo.utils.getRequestBodyAsJson
import com.jetpackcopmosedemo.utils.mock.endPointNotFoundResponse
import com.jetpackcopmosedemo.utils.mock.loginApiResponse
import com.jetpackcopmosedemo.utils.mock.loginCredsRequest
import com.jetpackcopmosedemo.utils.mock.methodNotSupportedResponse
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException


class MockInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val url = loginApiResponse[request.url.toString()] as ApiModel?

        // Check if the request is to the specific URL
        if (url != null) {
            if (request.method == url.method) {
                // Create a predefined response for the specific URL
                if (request.getRequestBodyAsJson() == loginCredsRequest)
                    return Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_1_1)
                        .code(200)
                        .message("Ok")
                        .body(
                            url.response.success.getMessageFromMap().toString()
                                .toResponseBody("application/json".toMediaType())
                        )
                        .build()
                else {
                    return Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_1_1)
                        .code(401)
                        .message("Error")
                        .body(
                            url.response.error.getMessageFromMap()
                                ?.toResponseBody("application/json".toMediaType())
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
                        methodNotSupportedResponse.getMessageFromMap()
                            ?.toResponseBody("application/json".toMediaType())
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
