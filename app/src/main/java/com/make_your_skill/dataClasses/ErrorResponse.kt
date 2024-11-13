package com.make_your_skill.dataClasses

data class ErrorResponse(
    val message: String,
    val error: String,
    val statusCode: Int
)
