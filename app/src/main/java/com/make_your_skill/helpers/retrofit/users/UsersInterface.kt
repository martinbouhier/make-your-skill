package com.make_your_skill.helpers.retrofit.users

import com.make_your_skill.models.user.FindByIdResponse
import com.make_your_skill.models.user.FindOneResponse
import com.make_your_skill.models.user.GetAllResponse
import com.make_your_skill.models.user.User
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface UsersInterface {
    @GET("/users")
    suspend fun getAll(): Response<GetAllResponse>

    @GET("/users/findOne")
    suspend fun findOne(): Response<FindOneResponse>

    @GET("/users/{id}")
    suspend fun findById(@Path("id") id: Int): Response<FindByIdResponse>

    @PATCH("/users/{id}")
    suspend fun update(@Path("id") id: Int)

    @DELETE("/users/{id}")
    suspend fun delete(@Path("id") id: Int)
}