package com.make_your_skill.helpers.retrofit.match

import com.make_your_skill.dataClasses.matches.MatchDataClass
import com.make_your_skill.dataClasses.matches.getMatch
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MatchService {
    @POST("matches")
    suspend fun createMatch(
        @Header("Authorization") token: String,
        @Body match: MatchDataClass
    ): Response<List<Any>>

    @GET("matches/findAllOfUser/{userId}")
    suspend fun findMatchByUserId(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int // Añadimos el parámetro userId para la busqueda
    ): Response<List<getMatch>>
}