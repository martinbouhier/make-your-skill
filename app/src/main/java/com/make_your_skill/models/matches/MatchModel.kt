package com.make_your_skill.models.matches

import com.google.gson.Gson
import com.make_your_skill.dataClasses.ErrorResponse
import com.make_your_skill.dataClasses.matches.CreateMatchDto
import com.make_your_skill.dataClasses.matches.MatchDataClass
import com.make_your_skill.dataClasses.matches.getMatch
import com.make_your_skill.helpers.retrofit.match.MatchService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MatchModel (private val matchService: MatchService) {
    private val ERROR_GETTING_MATCHES = "Error getting matches"
    private val ERROR_CREATING_MATCH = "Error creating match"

    fun findMatchesByUserId(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        listOfUserMatches: MutableStateFlow<List<getMatch>?>,
        token: String,
        userId: Int
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = matchService.findMatchByUserId(finalToken, userId)
                if (response.isSuccessful) {
                    listOfUserMatches.value = response.body() ?: emptyList()
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_CREATING_MATCH
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_CREATING_MATCH
            } finally {
                loading.value = false
            }
        }
    }

    fun createMatch(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        createMatchDto: CreateMatchDto,
        info: MutableStateFlow<Any?>,
        token: String
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = matchService.createMatch(finalToken, createMatchDto)
                if (response.isSuccessful) {
                    info.value = response.body()
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_GETTING_MATCHES
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_GETTING_MATCHES
            } finally {
                loading.value = false
            }
        }
    }
}