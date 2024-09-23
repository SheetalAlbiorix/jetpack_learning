package com.jetpackcopmosedemo.data.network.model

data class ApiModel(
    val method: String,
    val response: Response
)

data class Response(
    val success: Map<Any, Any>,
    val error: Map<Any, Any>,
)
