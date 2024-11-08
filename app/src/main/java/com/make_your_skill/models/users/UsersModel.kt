package com.make_your_skill.models.users

import com.google.gson.Gson
import com.make_your_skill.dataClasses.ErrorResponse
import com.make_your_skill.dataClasses.users.UserDataClass
import com.make_your_skill.dataClasses.usersSkills.body.GetUserSkillByUserId
import com.make_your_skill.helpers.retrofit.users.UserService
import com.make_your_skill.helpers.retrofit.usersSkills.UsersSkillsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersModel(private val usersService: UserService){
    private val ERROR_GETTING_USER_BY_ID = "Error getting user"

    fun getUserByUserId(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        userSearched: MutableStateFlow<UserDataClass?>,
        userId: Int,
        token: String,
        sessionCookie: String
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = usersService.getUserById(finalToken, sessionCookie,userId)
                if (response.isSuccessful) {
                    userSearched.value = response.body()
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_GETTING_USER_BY_ID
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_GETTING_USER_BY_ID
            } finally {
                loading.value = false
            }
        }
    }
}