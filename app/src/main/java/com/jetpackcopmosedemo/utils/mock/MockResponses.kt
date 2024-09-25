package com.jetpackcopmosedemo.utils.mock

val successResponse: Map<Any, Any> = mapOf("message" to "Api called successfully")
val eventsList: Map<Any, Any> = mapOf(
    "events" to listOf(
        "Event 1",
        "Event 2",
        "Event 3",
        "Event 4",
        "Event 5",
        "Event 6",
        "Event 7"
    )
)

val methodNotSupportedResponse: Map<Any, Any> = mapOf("message" to "Method Not Supported")
val endPointNotFoundResponse: Map<Any, Any> = mapOf("message" to "End point not found")
val unauthenticatedResponse: Map<Any, Any> = mapOf("message" to "User Not found")
val loginCredsRequest: Map<Any, Any> =
    mapOf("email" to "khush@gmail.com", "password" to "Khush@123")
