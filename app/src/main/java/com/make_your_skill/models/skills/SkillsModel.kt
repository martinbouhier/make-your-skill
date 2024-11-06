package com.make_your_skill.models.skills

import com.google.gson.Gson
import com.make_your_skill.dataClasses.ErrorResponse
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.helpers.retrofit.skills.SkillsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SkillsModel(private val skillsService: SkillsService) {
    private val ERROR_GETTING_SKILLS = "Error getting skills"

    fun getAllSkills(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        listOfSkills: MutableStateFlow<List<skillDataClass>>,
        token: String,
        sessionCookie: String
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = skillsService.getAllSkills(finalToken, sessionCookie)
                if (response.isSuccessful) {
                    listOfSkills.value = response.body() ?: emptyList()
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_GETTING_SKILLS
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_GETTING_SKILLS
            } finally {
                loading.value = false
            }
        }
    }
}