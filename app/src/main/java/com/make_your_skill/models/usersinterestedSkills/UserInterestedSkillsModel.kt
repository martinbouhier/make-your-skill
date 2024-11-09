package com.make_your_skill.models.usersinterestedSkills

import com.google.gson.Gson
import com.make_your_skill.dataClasses.ErrorResponse
import com.make_your_skill.dataClasses.usersInterestedSkills.body.AddUserInterestedSkill
import com.make_your_skill.dataClasses.usersInterestedSkills.body.DeleteUserInterestedSkill
import com.make_your_skill.dataClasses.usersInterestedSkills.body.GetUserInterestedSkillsById
import com.make_your_skill.dataClasses.usersSkills.body.DeleteUserSkill
import com.make_your_skill.dataClasses.usersSkills.body.GetUserSkillByUserId
import com.make_your_skill.helpers.retrofit.usersInterestedSkills.UserInterestedSkillsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersInterestedSkillsModel(private val userInterestedSkillsService: UserInterestedSkillsService) {
    private val ERROR_INSERTING_USER_INTERESTED_SKILL = "Error inserting user interested skill"
    private val ERROR_GETTING_USER_INTERESTED_SKILLS = "Error getting user interested skills"
    private val ERROR_DELETING_USER_INTERESTED_SKILL = "Error deleting user interested skill"

    fun addUserInterestedSkill(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        addUserInterestedSkillBody: AddUserInterestedSkill,
        token: String
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = userInterestedSkillsService.addUserInterestedSkill(finalToken, addUserInterestedSkillBody)
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

    fun getUserInterestedSkillsByUserId(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        listOfUserInterestedSkills: MutableStateFlow<List<GetUserInterestedSkillsById>>,
        userId: Int,
        token: String
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = userInterestedSkillsService.getUserInterestedSkillsByUserId(finalToken, userId)
                if (response.isSuccessful) {
                    listOfUserInterestedSkills.value = response.body() ?: emptyList()
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_GETTING_USER_INTERESTED_SKILLS
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_GETTING_USER_INTERESTED_SKILLS
            } finally {
                loading.value = false
            }
        }
    }

    fun deleteUserInterestedSkill(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        deleteUserSkill: DeleteUserSkill,
        token: String
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = userInterestedSkillsService.deleteUserInterestedSkill(finalToken, deleteUserSkill.userId,deleteUserSkill.skillId)
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