package com.make_your_skill.ui.screens.singIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.auth.AuthService
import com.make_your_skill.models.auth.AuthModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SingInViewModel : ViewModel() {
    val authService: AuthService = RetrofitServiceFactory.makeRetrofitService<AuthService>()
    private val authModel = AuthModel(authService)

    private val ERROR_LOGIN_IN = "Error logging in"

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    private val _signInInfo = MutableStateFlow<SignInDto?>(null)
    val signInInfo: StateFlow<SignInDto?> get() = _signInInfo

    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> get() = _email
    fun setEmail(newEmail: String) { _email.value = newEmail }

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> get() = _password
    fun setPassword(newPassword: String) { _password.value = newPassword }

    val onEmailChange: (String) -> Unit = { newEmail ->
        clearError()
        setEmail(newEmail)
    }

    val onPasswordChange: (String) -> Unit = { newPassword ->
        clearError()
        setPassword(newPassword)
    }

    fun clearError() {
        _error.value = null
    }

    val onClick = {
        if (email.value != "" && password.value != ""){
            val signInBody = SignInBody(email.value, password.value)
            authModel.signIn(
                scope = viewModelScope,
                signInBody = signInBody,
                loading = _loading,
                error = _error,
                signInInfo = _signInInfo
            )
        }
    }
}