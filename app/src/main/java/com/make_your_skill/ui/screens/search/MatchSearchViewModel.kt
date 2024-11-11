package com.make_your_skill.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.make_your_skill.dataClasses.users.UserDataClass
import com.make_your_skill.dataClasses.usersInterestedSkills.body.GetUserInterestedSkillsById
import com.make_your_skill.dataClasses.usersInterestedSkills.body.InterestAddedDataClass
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
class MatchSearchViewModel @Inject constructor() : ViewModel() {
    val usersInterestedSkillsService: UserInterestedSkillsService = RetrofitServiceFactory.makeRetrofitService<UserInterestedSkillsService>()
    private val usersInterestedSkillsModel = UsersInterestedSkillsModel(usersInterestedSkillsService)

    val usersSkillsService: UsersSkillsService = RetrofitServiceFactory.makeRetrofitService<UsersSkillsService>()
    private val usersSkillModel = UsersSkillsModel(usersSkillsService)

    val usersService: UserService = RetrofitServiceFactory.makeRetrofitService<UserService>()
    private val usersModel = UsersModel(usersService)

    private val _listOfUserInterestedSkills = MutableStateFlow<List<GetUserInterestedSkillsById>>(emptyList())
    val listOfUserInterestedSkills: StateFlow<List<GetUserInterestedSkillsById>> get() = _listOfUserInterestedSkills

    private val _loadingInterest = MutableStateFlow<Boolean>(false)
    val loadingInterest: StateFlow<Boolean> get() = _loadingInterest

    private val _errorInterest = MutableStateFlow<String?>(null)
    val errorInterest: StateFlow<String?> get() = _errorInterest
    fun setErrorInterest(newError: String) { _errorInterest.value = newError }

    private val _skillSelected = MutableStateFlow<InterestAddedDataClass?>(null)
    val skillSelected: StateFlow<InterestAddedDataClass?> get() = _skillSelected
    fun setSkillSelected(newSkill: InterestAddedDataClass) { _skillSelected.value = newSkill   }

    private val _usersSearched = MutableStateFlow<List<UserDataClass>>(emptyList())
    val usersSearched: StateFlow<List<UserDataClass>> get() = _usersSearched

    private val _loadingUsersSearch = MutableStateFlow<Boolean>(false)
    val loadingUsersSearch: StateFlow<Boolean> get() = _loadingUsersSearch

    private val _errorUsersSearched = MutableStateFlow<String?>(null)
    val errorUsersSearched: StateFlow<String?> get() = _errorUsersSearched
    fun setErrorUsersSearched(newError: String) { _errorUsersSearched.value = newError }

    private val _listOfUserSkills = MutableStateFlow<List<GetUserSkillByUserId>>(emptyList())
    val listOfUserSkills: StateFlow<List<GetUserSkillByUserId>> get() = _listOfUserSkills

    private val _loadingSkills = MutableStateFlow<Boolean>(false)
    val loadingSkills: StateFlow<Boolean> get() = _loadingSkills

    private val _errorSkills = MutableStateFlow<String?>(null)
    val errorSkills: StateFlow<String?> get() = _errorSkills
    fun setErrorSkills(newError: String) { _errorSkills.value = newError }

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

    fun getUserSkillByUserId(
        token: String,
        userId: Int
    ){
        usersSkillModel.getUserSkillsByUserId(
            scope = viewModelScope,
            loading = _loadingSkills,
            error = _errorSkills,
            listOfUserSkills = _listOfUserSkills,
            userId = userId,
            token = token
        )
    }

    fun findManyBySkillsAndInterests(
        token: String,
        skillsIds: String,
        interestsIds: String? = null
    ){
        usersModel.findManyBySkillsAndInterests(
            scope = viewModelScope,
            loading = _loadingUsersSearch,
            error = _errorUsersSearched,
            info = _usersSearched,
            token = token,
            skillsIds = skillsIds,
            interestsIds = interestsIds
        )
    }
}