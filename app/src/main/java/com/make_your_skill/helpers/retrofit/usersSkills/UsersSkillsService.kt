package com.make_your_skill.helpers.retrofit.usersSkills

import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.dataClasses.usersSkills.body.AddUserSkill
import com.make_your_skill.dataClasses.usersSkills.body.DeleteUserSkill
import com.make_your_skill.dataClasses.usersSkills.body.GetUserSkillByUserId
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersSkillsService {
    @POST("users-skills")
    suspend fun addUserSkill(
        @Header("Authorization") token: String,
        @Body addUserSkill: AddUserSkill
    ): Response<Any>

    @GET("users-skills/findByUser/{userId}")
    suspend fun getUserSkillsByUserId(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int
    ): Response<List<GetUserSkillByUserId>>

    @PATCH("users-skills/{userId}")
    suspend fun updateUserSkill(
        @Header("Authorization") token: String,
        @Body editUserSkill: AddUserSkill
    ): Response<Any>

    @DELETE("users-skills/byUserSkill")
    suspend fun deleteUserSkill(
        @Header("Authorization") token: String,
        @Query("userId") userId: Int,
        @Query("skillId") skillId: Int
    ): Response<Any>
}