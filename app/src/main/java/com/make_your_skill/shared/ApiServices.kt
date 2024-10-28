package com.make_your_skill.shared

import com.make_your_skill.model.MakeYourSkillApiModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("endpoint/get")
    suspend fun getSkills(): Response<List<MakeYourSkillApiModel>>

    @POST("endpoint/post")
    suspend fun postskill(@Body newItem: MakeYourSkillApiModel): Response<MakeYourSkillApiModel>

    @PUT("endpoint/put/{id}")
    suspend fun updateskill(
        @Path("id") itemId: Int,
        @Body updatedItem: MakeYourSkillApiModel
    ): Response<MakeYourSkillApiModel>

    @DELETE("endpoint/delete/{id}")
    suspend fun deleteItemskill(@Path("id") itemId: Int): Response<Unit>
}