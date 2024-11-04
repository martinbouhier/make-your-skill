package com.make_your_skill.ui.screens.createNewAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.auth.AuthService
import com.make_your_skill.helpers.validations.isValidEmail
import com.make_your_skill.helpers.validations.isValidPassword
import com.make_your_skill.models.auth.AuthModel
import com.make_your_skill.ui.navigation.AppRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreateNewAcoountViewModel: ViewModel() {
    private val MUST_COMPLETE_INPUTS = "Must complete inputs"
    private val INVALID_EMAIL = "Email is not valid"
    private val INVALID_PASSWORD = "Password must have a minimum of eight characters, at least one uppercase letter, one lowercase letter, one number and one special character"
    private val PASSWORDS_MUST_MATCH = "Passwords must match"

    val authService: AuthService = RetrofitServiceFactory.makeRetrofitService<AuthService>()
    private val authModel = AuthModel(authService)

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error
    fun setError(newError: String) { _error.value = newError }

    private val _registerInfo = MutableStateFlow<Any?>(null)
    val registerInfo: StateFlow<Any?> get() = _registerInfo

    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> get() = _email
    fun setEmail(newEmail: String) { _email.value = newEmail }

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> get() = _password
    fun setPassword(newPassword: String) { _password.value = newPassword }

    private val _reWritePassword = MutableStateFlow<String>("")
    val reWritePassword: StateFlow<String> get() = _reWritePassword
    fun setreWritepassword(newPassword: String) { _reWritePassword.value = newPassword }

    val onEmailChange: (String) -> Unit = { newEmail ->
        clearError()
        setEmail(newEmail)
    }

    val onPasswordChange: (String) -> Unit = { newPassword ->
        clearError()
        setPassword(newPassword)
    }

    val onReWritePasswordChange: (String) -> Unit = { newPassword ->
        clearError()
        setreWritepassword(newPassword)
    }

    fun clearError() {
        _error.value = null
    }

    val onClick: (NavHostController) -> Unit = {navController ->
        if (email.value == "" || password.value == "" || reWritePassword.value == ""){
            setError(MUST_COMPLETE_INPUTS)
        }
        else if (!isValidEmail(email.value)){
            setError(INVALID_EMAIL)
        }
        else if (!isValidPassword(password.value)){
            setError(INVALID_PASSWORD)
        }
        else if (password.value != reWritePassword.value){
            setError(PASSWORDS_MUST_MATCH)
        }
        else {
            navController.navigate(AppRoutes.FIRST_NAME_SCREEN)
        }
    }
}