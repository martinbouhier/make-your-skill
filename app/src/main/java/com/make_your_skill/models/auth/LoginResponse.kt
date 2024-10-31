package com.make_your_skill.models.auth

data class LoginResponse (
    val result: Auth
) {
    fun toModel() = Auth(
        user = result.user,
        tokens = result.tokens
        )
}