package com.make_your_skill.helpers.retrofit.usersInterestedSkills

import com.make_your_skill.dataClasses.usersInterestedSkills.body.AddUserInterestedSkill
import com.make_your_skill.dataClasses.usersInterestedSkills.body.GetUserInterestedSkillsById
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserInterestedSkillsService {
    @POST("users-interested-skills")
    suspend fun addUserInterestedSkill(
        @Header("Authorization") token: String,
        @Header("Cookie") sessionCookie: String? = null, // Añadimos la cookie como parámetro opcional
        @Body addUserInterestedSkill: AddUserInterestedSkill
    ): Response<Any>

    @GET("users-interested-skills/findByUser/{userId}")
    suspend fun getUserInterestedSkillsByUserId(
        @Header("Authorization") token: String,
        @Header("Cookie") sessionCookie: String? = null, // Añadimos la cookie como parámetro opcional
        @Path("userId") userId: Int
    ): Response<List<GetUserInterestedSkillsById>>
}