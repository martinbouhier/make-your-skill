package com.make_your_skill.models.usersinterestedSkills

import com.google.gson.Gson
import com.make_your_skill.dataClasses.ErrorResponse
import com.make_your_skill.dataClasses.usersInterestedSkills.body.AddUserInterestedSkill
import com.make_your_skill.helpers.retrofit.usersInterestedSkills.UserInterestedSkillsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersInterestedSkillsModel(private val userInterestedSkillsService: UserInterestedSkillsService) {
    private val ERROR_INSERTING_USER_INTERESTED_SKILL = "Error inserting user interested skill"

    fun addUserInterestedSkill(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        addUserInterestedSkillBody: AddUserInterestedSkill,
        token: String,
        sessionCookie: String
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = userInterestedSkillsService.addUserInterestedSkill(finalToken, sessionCookie,addUserInterestedSkillBody)
                if (response.isSuccessful) {
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_INSERTING_USER_INTERESTED_SKILL
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_INSERTING_USER_INTERESTED_SKILL
            } finally {
                loading.value = false
            }
        }
    }
}