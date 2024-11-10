package com.make_your_skill.helpers.retrofit.users

import com.make_your_skill.dataClasses.users.FindManyBySkillsAndInterests
import com.make_your_skill.dataClasses.users.IncreaseVotesBody
import com.make_your_skill.dataClasses.users.UserDataClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    @DELETE("users/{userId}")
    suspend fun deleteUser(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int, // Añadimos el parámetro userId
    ): Response<Any>

    @GET("users/findManyBySkillsAndInterests")
    suspend fun findManyBySkillsAndInterests(
        @Header("Authorization") token: String,
        @Query("skillsIds") skillsIds: String,
        @Query("interestsIds") interestsIds: String? // Parámetro opcional
    ): Response<List<UserDataClass>>
}