package com.make_your_skill.helpers.retrofit.usersInterestedSkills

import com.make_your_skill.dataClasses.usersInterestedSkills.body.AddUserInterestedSkill
import com.make_your_skill.dataClasses.usersInterestedSkills.body.DeleteUserInterestedSkill
import com.make_your_skill.dataClasses.usersInterestedSkills.body.GetUserInterestedSkillsById
import com.make_your_skill.dataClasses.usersSkills.body.DeleteUserSkill
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserInterestedSkillsService {
    @POST("users-interested-skills")
    suspend fun addUserInterestedSkill(
        @Header("Authorization") token: String,
        @Body addUserInterestedSkill: AddUserInterestedSkill
    ): Response<Any>

    @GET("users-interested-skills/findByUser/{userId}")
    suspend fun getUserInterestedSkillsByUserId(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int
    ): Response<List<GetUserInterestedSkillsById>>

    @DELETE("users-interested-skills/byUserSkill")
    suspend fun deleteUserInterestedSkill(
        @Header("Authorization") token: String,
        @Query("userId") userId: Int,
        @Query("skillId") skillId: Int
    ): Response<Any>
}