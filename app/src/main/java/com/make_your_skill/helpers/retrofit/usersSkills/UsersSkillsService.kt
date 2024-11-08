package com.make_your_skill.helpers.retrofit.usersSkills

import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.dataClasses.usersSkills.body.AddUserSkill
import com.make_your_skill.dataClasses.usersSkills.body.GetUserSkillByUserId
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UsersSkillsService {
    @POST("users-skills")
    suspend fun addUserSkill(
        @Header("Authorization") token: String,
        @Header("Cookie") sessionCookie: String? = null, // Añadimos la cookie como parámetro opcional
        @Body addUserSkill: AddUserSkill
    ): Response<Any>

    @GET("users-skills/findByUser/{userId}")
    suspend fun getUserSkillsByUserId(
        @Header("Authorization") token: String,
        @Header("Cookie") sessionCookie: String? = null, // Añadimos la cookie como parámetro opcional
        @Path("userId") userId: Int
    ): Response<List<GetUserSkillByUserId>>

    @DELETE("users-skills/{skillId}")
    suspend fun deleteUserSkillsByUserId(
        @Header("Authorization") token: String,
        @Header("Cookie") sessionCookie: String? = null, // Añadimos la cookie como parámetro opcional
        @Path("skillId") skillId: Int
    ): Response<List<Any>>
}