package com.make_your_skill.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.make_your_skill.dataClasses.users.UserDataClass
import com.make_your_skill.dataClasses.usersInterestedSkills.body.GetUserInterestedSkillsById
import com.make_your_skill.dataClasses.usersSkills.body.GetUserSkillByUserId
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.users.UserService
import com.make_your_skill.helpers.retrofit.usersInterestedSkills.UserInterestedSkillsService
import com.make_your_skill.helpers.retrofit.usersSkills.UsersSkillsService
import com.make_your_skill.models.users.UsersModel
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

    val usersService: UserService = RetrofitServiceFactory.makeRetrofitService<UserService>()
    private val usersModel = UsersModel(usersService)

    private val _userSearched = MutableStateFlow<UserDataClass?>(null)
    val userSearched: StateFlow<UserDataClass?> get() = _userSearched

    private val _loadingUserSearched = MutableStateFlow<Boolean>(false)
    val loadingUserSearched: StateFlow<Boolean> get() = _loadingUserSearched

    private val _errorUserSearched = MutableStateFlow<String?>(null)
    val errorUserSearched: StateFlow<String?> get() = _errorUserSearched
    fun setErrorUserSearched(newError: String) { _errorUserSearched.value = newError }

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


    fun getUserById(
        token: String,
        userId: Int
    ){
        usersModel.getUserByUserId(
            scope = viewModelScope,
            loading = _loadingUserSearched,
            error = _errorUserSearched,
            userSearched = _userSearched,
            userId = userId,
            token = token
        )
    }

    fun getUserSkillByUserId(
        token: String,
        userId: Int
    ){
        usersSkillModel.getUserSkillsByUserId(
            scope = viewModelScope,
            loading = _loading,
            error = _error,
            listOfUserSkills = _listOfUserSkills,
            userId = userId,
            token = token
        )
    }

    fun getUserInterestedSkillByUserId(
        token: String,
        userId: Int
    ){
        usersInterestedSkillsModel.getUserInterestedSkillsByUserId(
            scope = viewModelScope,
            loading = _loadingInterest,
            error = _errorInterest,
            listOfUserInterestedSkills = _listOfUserInterestedSkills,
            userId = userId,
            token = token
        )
    }
}