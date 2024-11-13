package com.make_your_skill.dataClasses.auth.body

data class RegisterBody(
    val firstname: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val dateOfBirth: String,
    val password: String,
    val isActive: Boolean
)
