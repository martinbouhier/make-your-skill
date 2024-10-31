package com.make_your_skill.models.user

data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val email: String,
    val isActive: Boolean,
    val rating: Int,
    val dateOfBirth: String,
    val createdAt: String,
    val updatedAt: String
)