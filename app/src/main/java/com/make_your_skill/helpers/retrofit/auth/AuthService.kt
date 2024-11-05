package com.make_your_skill.helpers.retrofit.auth

import com.make_your_skill.dataClasses.auth.body.RegisterBody
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    suspend fun login(
        @Body signInBody: SignInBody
    ): Response<SignInDto>

    @POST("auth/register")
    suspend fun register(
        @Body registerBody: RegisterBody
    ): Response<Any>
}