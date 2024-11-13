package com.make_your_skill.ui.screens.profileSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.make_your_skill.dataClasses.usersInterestedSkills.body.GetUserInterestedSkillsById
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.users.UserService
import com.make_your_skill.models.users.UsersModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileSettingsViewModel : ViewModel(){
    val usersService: UserService = RetrofitServiceFactory.makeRetrofitService<UserService>()
    private val usersModel = UsersModel(usersService)

    private val _showDeleteAccountPopUp = MutableStateFlow<Boolean>(false)
    val showDeleteAccountPopUp: StateFlow<Boolean> get() = _showDeleteAccountPopUp
    fun setShowDeleteAccountPopUp(newState: Boolean) { _showDeleteAccountPopUp.value = newState }

    private val _loadingDelete = MutableStateFlow<Boolean>(false)
    val loadingDelete: StateFlow<Boolean> get() = _loadingDelete

    private val _errorDelete = MutableStateFlow<String?>(null)
    val errorDelete: StateFlow<String?> get() = _errorDelete
    fun setErrorDelete(newError: String) { _errorDelete.value = newError }

    private val _deletedUser = MutableStateFlow<Any?>(null)
    val deletedUser: StateFlow<Any?> get() = _deletedUser

    fun deleteUser(
        token: String,
        userId: Int
    ){
        usersModel.deleteUser(
            scope = viewModelScope,
            loading = _loadingDelete,
            error = _errorDelete,
            userDelete = _deletedUser,
            userId = userId,
            token = token
        )
    }
}