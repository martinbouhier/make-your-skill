package com.make_your_skill.helpers.retrofit.auth

import com.make_your_skill.models.auth.Auth

interface AuthServiceInterface {
    suspend fun login(): Auth?
}