package com.make_your_skill.helpers.retrofit.usersSkills

import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.dataClasses.usersSkills.body.AddUserSkill
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UsersSkillsService {
    @POST("users-skills")
    suspend fun addUserSkill(
        @Header("Authorization") token: String,
        @Header("Cookie") sessionCookie: String? = null, // Añadimos la cookie como parámetro opcional
        @Body addUserSkill: AddUserSkill
    ): Response<Any>
}