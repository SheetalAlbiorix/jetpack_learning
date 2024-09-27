package com.jetpackcopmosedemo.utils.mock

import com.jetpackcopmosedemo.data.network.model.ApiModel
import com.jetpackcopmosedemo.data.network.model.Response

val mockApisResponses: Map<Any, Any> = mapOf(
    "https://example.com/api/login" to
            ApiModel(
                "POST",
                Response(
                    success = successResponse,
                    error = unauthenticatedResponse
                )
            ),
    "https://example.com/api/getEvents" to
            ApiModel(
                "GET",
                Response(
                    success = eventsList,
                    error = unauthenticatedResponse
                )
            ),
    "https://example.com/api/deleteEvents/0" to
            ApiModel(
                "DELETE",
                Response(
                    success = eventsList,
                    error = unauthenticatedResponse
                )
            ),
    "https://example.com/api/editEvents/0" to
            ApiModel(
                "PATCH",
                Response(
                    success = eventsList,
                    error = unauthenticatedResponse
                )
            )
)