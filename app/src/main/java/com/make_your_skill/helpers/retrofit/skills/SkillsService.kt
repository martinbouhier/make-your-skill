package com.make_your_skill.helpers.retrofit.skills

import com.make_your_skill.dataClasses.skills.skillDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface SkillsService {
    @GET("Skills")
    suspend fun getAllSkills(
        @Header("Authorization") token: String,
        @Header("Cookie") sessionCookie: String? = null // Añadimos la cookie como parámetro opcional
    ): Response<List<skillDataClass>>
}