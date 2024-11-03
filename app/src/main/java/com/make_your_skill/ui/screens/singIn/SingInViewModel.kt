package com.make_your_skill.ui.screens.singIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.auth.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SingInViewModel : ViewModel() {
    private val ERROR_LOGIN_IN = "Error logging in"

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    private val _signInInfo = MutableStateFlow<SignInDto?>(null)
    val signInInfo: StateFlow<SignInDto?> get() = _signInInfo

    val authService: AuthService = RetrofitServiceFactory.makeRetrofitService<AuthService>()

    fun signIn(signInBody: SignInBody) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response: SignInDto = authService.login(signInBody)
                _signInInfo.value = response
                _error.value = null
            } catch (e: Exception) {
                _error.value = ERROR_LOGIN_IN
            } finally {
                _loading.value = false
            }
        }
    }
}