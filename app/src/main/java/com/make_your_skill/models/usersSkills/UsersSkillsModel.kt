package com.make_your_skill.models.usersSkills

import com.google.gson.Gson
import com.make_your_skill.dataClasses.ErrorResponse
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.dataClasses.usersSkills.body.AddUserSkill
import com.make_your_skill.dataClasses.usersSkills.body.DeleteUserSkill
import com.make_your_skill.dataClasses.usersSkills.body.GetUserSkillByUserId
import com.make_your_skill.helpers.retrofit.skills.SkillsService
import com.make_your_skill.helpers.retrofit.usersSkills.UsersSkillsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersSkillsModel(private val usersSkillsService: UsersSkillsService) {
    private val ERROR_INSERTING_USER_SKILL = "Error inserting user skill"
    private val ERROR_GETTING_USER_SKILLS = "Error getting user skills"
    private val ERROR_DELETING_USER_SKILL = "Error deleting user skill"

    fun addUserSkill(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        addUserSkillBody: AddUserSkill,
        token: String
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = usersSkillsService.addUserSkill(finalToken, addUserSkillBody)
                if (response.isSuccessful) {
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_INSERTING_USER_SKILL
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_INSERTING_USER_SKILL
            } finally {
                loading.value = false
            }
        }
    }

    fun getUserSkillsByUserId(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        listOfUserSkills: MutableStateFlow<List<GetUserSkillByUserId>>,
        userId: Int,
        token: String
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = usersSkillsService.getUserSkillsByUserId(finalToken, userId)
                if (response.isSuccessful) {
                    listOfUserSkills.value = response.body() ?: emptyList()
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_GETTING_USER_SKILLS
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_GETTING_USER_SKILLS
            } finally {
                loading.value = false
            }
        }
    }

    fun deleteUserSkill(
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
                val response = usersSkillsService.deleteUserSkill(finalToken, deleteUserSkill.userId,deleteUserSkill.skillId)
                if (response.isSuccessful) {
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_INSERTING_USER_SKILL
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_DELETING_USER_SKILL
            } finally {
                loading.value = false
            }
        }
    }
}