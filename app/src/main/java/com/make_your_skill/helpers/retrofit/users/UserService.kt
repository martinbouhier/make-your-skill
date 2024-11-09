package com.make_your_skill.helpers.retrofit.users

import com.make_your_skill.dataClasses.auth.body.ChangePasswordBody
import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.dataClasses.users.IncreaseVotesBody
import com.make_your_skill.dataClasses.users.UserDataClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @GET("users/{userId}")
    suspend fun getUserById(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int, // Añadimos el parámetro userId
    ): Response<UserDataClass>

    @POST("users/increaseVotes")
    suspend fun increaseVotes(
        @Header("Authorization") token: String,
        @Body increaseVotesBody: IncreaseVotesBody
    ): Response<Any>
}