package com.make_your_skill.ui.screens.singIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.auth.AuthService
import com.make_your_skill.helpers.validations.isValidEmail
import com.make_your_skill.models.auth.AuthModel
import com.make_your_skill.viewModel.MakeYourSkillViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(): ViewModel() {
    val authService: AuthService = RetrofitServiceFactory.makeRetrofitService<AuthService>()
    private val authModel = AuthModel(authService)

    private val ERROR_LOGIN_IN = "Error logging in"
    private val MUST_COMPLETE_INPUTS = "Must complete inputs"
    private val INVALID_EMAIL = "Email is not valid"

    private val _isLoggedIn = MutableStateFlow<Boolean>(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn
    fun setIsLoggedIn(newState: Boolean) { _isLoggedIn.value = newState }

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error
    fun setError(newError: String) { _error.value = newError }

    private val _signInInfo = MutableStateFlow<SignInDto?>(null)
    val signInInfo: StateFlow<SignInDto?> get() = _signInInfo

    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> get() = _email
    fun setEmail(newEmail: String) { _email.value = newEmail }

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> get() = _password
    fun setPassword(newPassword: String) { _password.value = newPassword }

    fun getToken(): String{
        var token = ""
        if (signInInfo.value != null){
            token = signInInfo.value!!.tokens.token
        }
        return token
    }

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

    fun login(
        email: MutableStateFlow<String>,
        password: MutableStateFlow<String>,
        loading:  MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        signInInfo: MutableStateFlow<SignInDto?>,
        isLoggedIn: MutableStateFlow<Boolean>
    ){
        val signInBody = SignInBody(email.value, password.value)
        authModel.signIn(
            scope = viewModelScope,
            signInBody = signInBody,
            loading = loading,
            error = error,
            signInInfo = signInInfo,
            isLoggedIn = isLoggedIn
        )
    }

    val onLogin = {
        if (email.value.isEmpty() || password.value.isEmpty()) {
            setError(MUST_COMPLETE_INPUTS)
        } else if (!isValidEmail(email.value)) {
            setError(INVALID_EMAIL)
        } else {
            login(_email,_password,_loading,_error,_signInInfo,_isLoggedIn)
        }
    }
}