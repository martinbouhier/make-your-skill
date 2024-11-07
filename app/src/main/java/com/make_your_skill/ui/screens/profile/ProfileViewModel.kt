package com.make_your_skill.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.make_your_skill.dataClasses.usersInterestedSkills.body.GetUserInterestedSkillsById
import com.make_your_skill.dataClasses.usersSkills.body.GetUserSkillByUserId
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.usersInterestedSkills.UserInterestedSkillsService
import com.make_your_skill.helpers.retrofit.usersSkills.UsersSkillsService
import com.make_your_skill.models.usersSkills.UsersSkillsModel
import com.make_your_skill.models.usersinterestedSkills.UsersInterestedSkillsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    val usersSkillsService: UsersSkillsService = RetrofitServiceFactory.makeRetrofitService<UsersSkillsService>()
    private val usersSkillModel = UsersSkillsModel(usersSkillsService)

    val usersInterestedSkillsService: UserInterestedSkillsService = RetrofitServiceFactory.makeRetrofitService<UserInterestedSkillsService>()
    private val usersInterestedSkillsModel = UsersInterestedSkillsModel(usersInterestedSkillsService)

    private val _listOfUserSkills = MutableStateFlow<List<GetUserSkillByUserId>>(emptyList())
    val listOfUserSkills: StateFlow<List<GetUserSkillByUserId>> get() = _listOfUserSkills

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error
    fun setError(newError: String) { _error.value = newError }

    private val _listOfUserInterestedSkills = MutableStateFlow<List<GetUserInterestedSkillsById>>(emptyList())
    val listOfUserInterestedSkills: StateFlow<List<GetUserInterestedSkillsById>> get() = _listOfUserInterestedSkills

    private val _loadingInterest = MutableStateFlow<Boolean>(false)
    val loadingInterest: StateFlow<Boolean> get() = _loadingInterest

    private val _errorInterest = MutableStateFlow<String?>(null)
    val errorInterest: StateFlow<String?> get() = _errorInterest
    fun setErrorInterest(newError: String) { _errorInterest.value = newError }

    fun getUserSkillByUserId(
        token: String,
        sessionCookie: String,
        userId: Int
    ){
        usersSkillModel.getUserSkillsByUserId(
            scope = viewModelScope,
            loading = _loading,
            error = _error,
            listOfUserSkills = _listOfUserSkills,
            userId = userId,
            token = token,
            sessionCookie = sessionCookie
        )
    }

    fun getUserInterestedSkillByUserId(
        token: String,
        sessionCookie: String,
        userId: Int
    ){
        usersInterestedSkillsModel.getUserInterestedSkillsByUserId(
            scope = viewModelScope,
            loading = _loadingInterest,
            error = _errorInterest,
            listOfUserInterestedSkills = _listOfUserInterestedSkills,
            userId = userId,
            token = token,
            sessionCookie = sessionCookie
        )
    }
}