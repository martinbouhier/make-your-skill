package com.make_your_skill.model

data class RegisterRequest(
    val firstname: String,
    val lastname: String,
    val email: String,
    val dateOfBirth: String,
    val password: String,
    val isActive: Boolean
)