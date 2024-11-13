package com.make_your_skill.helpers.retrofit.auth

import com.make_your_skill.dataClasses.auth.body.ChangePasswordBody
import com.make_your_skill.dataClasses.auth.body.RegisterBody
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    @POST("auth/login")
    suspend fun login(
        @Body signInBody: SignInBody
    ): Response<SignInDto>

    @POST("auth/register")
    suspend fun register(
        @Body registerBody: RegisterBody
    ): Response<Any>

    @POST("auth/changePassword/{userId}")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int, // Parámetro de ruta
        @Body changePasswordBody: ChangePasswordBody
    ): Response<Any>
}