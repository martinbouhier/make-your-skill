package com.make_your_skill.helpers.retrofit.auth

import com.make_your_skill.models.auth.LoginResponse
import com.make_your_skill.models.user.FindByIdResponse
import com.make_your_skill.models.user.FindOneResponse
import com.make_your_skill.models.user.GetAllResponse
import com.make_your_skill.models.user.User
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface AuthInterface {
    @GET("/auth/login")
    suspend fun login(): Response<LoginResponse>
}