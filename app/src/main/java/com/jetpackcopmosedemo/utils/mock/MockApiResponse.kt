package com.jetpackcopmosedemo.utils.mock

import com.jetpackcopmosedemo.data.network.model.ApiModel
import com.jetpackcopmosedemo.data.network.model.Response

val loginApiResponse: Map<Any, Any> = mapOf(
    "https://example.com/api/login" to
            ApiModel(
                "POST",
                Response(
                    success = listResponse,
                    error = unauthenticatedResponse
                )
            )
)