package com.make_your_skill.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MakeYourSkillViewModel @Inject constructor(): ViewModel() {
    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> = _isLoggedIn
    fun setIsLoggedIn(newState: Boolean) { _isLoggedIn.value = newState }

    private val _userInfo = mutableStateOf<SignInDto?>(null)
    val userInfo: State<SignInDto?> = _userInfo
    fun setUserInfo(newUserInfo: SignInDto) { _userInfo.value = newUserInfo }

    fun register(){}
}