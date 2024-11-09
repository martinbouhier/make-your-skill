package com.make_your_skill.ui.screens.matchSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.make_your_skill.dataClasses.usersInterestedSkills.body.GetUserInterestedSkillsById
import com.make_your_skill.dataClasses.usersInterestedSkills.body.InterestAddedDataClass
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.usersInterestedSkills.UserInterestedSkillsService
import com.make_your_skill.models.usersinterestedSkills.UsersInterestedSkillsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MatchSearchViewModel @Inject constructor() : ViewModel() {
    val usersInterestedSkillsService: UserInterestedSkillsService = RetrofitServiceFactory.makeRetrofitService<UserInterestedSkillsService>()
    private val usersInterestedSkillsModel = UsersInterestedSkillsModel(usersInterestedSkillsService)

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