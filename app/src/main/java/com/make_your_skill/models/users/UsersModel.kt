package com.make_your_skill.models.users

import com.google.gson.Gson
import com.make_your_skill.dataClasses.ErrorResponse
import com.make_your_skill.dataClasses.users.FindManyBySkillsAndInterests
import com.make_your_skill.dataClasses.users.IncreaseVotesBody
import com.make_your_skill.dataClasses.users.UserDataClass
import com.make_your_skill.helpers.retrofit.users.UserService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UsersModel(private val usersService: UserService){
    private val ERROR_GETTING_USER_BY_ID = "Error getting user"
    private val ERROR_INCREASING_VOTES = "Error increasing votes"
    private val ERROR_DELETING_USER = "Error deleting user"
    private val ERROR_FINDING_MANY = "Error finding users"

    fun getUserByUserId(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        userSearched: MutableStateFlow<UserDataClass?>,
        userId: Int,
        token: String
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = usersService.getUserById(finalToken, userId)
                if (response.isSuccessful) {
                    userSearched.value = response.body()
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_DELETING_USER
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_DELETING_USER
            } finally {
                loading.value = false
            }
        }
    }

    fun increaseVotes(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        increaseInfo: MutableStateFlow<Any?>,
        token: String,
        increaseVotesBody: IncreaseVotesBody
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = usersService.increaseVotes(finalToken, increaseVotesBody)
                if (response.isSuccessful) {
                    increaseInfo.value = response.body()
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_INCREASING_VOTES
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_INCREASING_VOTES
            } finally {
                loading.value = false
            }
        }
    }

    fun deleteUser(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        userDelete: MutableStateFlow<Any?>,
        userId: Int,
        token: String
    ) {
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = usersService.deleteUser(finalToken, userId)
                if (response.isSuccessful) {
                    userDelete.value = response.body()
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

    fun findManyBySkillsAndInterests(
        scope: CoroutineScope,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        info: MutableStateFlow<List<UserDataClass>>,
        token: String,
        skillsIds: String,
        interestsIds: String?
    ) {
        val findManyBySkillsAndInterests = FindManyBySkillsAndInterests(skillsIds,interestsIds)
        scope.launch {
            loading.value = true
            try {
                val finalToken: String = "Bearer $token"
                val response = usersService.findManyBySkillsAndInterests(
                    finalToken,
                    findManyBySkillsAndInterests.skillsIds,
                    findManyBySkillsAndInterests.interestsIds
                )
                if (response.isSuccessful) {
                    info.value = response.body()!!
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_FINDING_MANY
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = e.toString()
            } finally {
                loading.value = false
            }
        }
    }
}