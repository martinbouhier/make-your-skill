package com.make_your_skill.ui.screens.newPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.make_your_skill.dataClasses.auth.body.ChangePasswordBody
import com.make_your_skill.helpers.functions.isValidEmail
import com.make_your_skill.helpers.functions.isValidPassword
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.auth.AuthService
import com.make_your_skill.models.auth.AuthModel
import com.make_your_skill.ui.navigation.AppRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NewPasswordViewModel: ViewModel() {
    private val MUST_COMPLETE_INPUTS = "Must complete inputs"
    private val INVALID_PASSWORD = "Password must have a minimum of eight characters, at least one uppercase letter, one lowercase letter, one number and one special character"
    private val PASSWORDS_MUST_MATCH = "New passwords must match"

    private val PASSWORDS_MUST_BE_DIFFERENT = "New password must be different from the old password" // Nuevo mensaje de error

    private val MAX_PASSWORD_LENGTH = 40
    private val MAX_PASSWORD_LENGTH_MESSAGE = "Password can't have more than $MAX_PASSWORD_LENGTH characters"


    val authService: AuthService = RetrofitServiceFactory.makeRetrofitService<AuthService>()
    private val authModel = AuthModel(authService)

    private val _oldPassword = MutableStateFlow<String>("")
    val oldPassword: StateFlow<String> get() = _oldPassword
    fun setOldPassword(oldPassword: String) { _oldPassword.value = oldPassword }

    private val _newPassword = MutableStateFlow<String>("")
    val newPassword: StateFlow<String> get() = _newPassword
    fun setNewPassword(newPassword: String) { _newPassword.value = newPassword }

    private val _reWriteNewPassword = MutableStateFlow<String>("")
    val reWriteNewPassword: StateFlow<String> get() = _reWriteNewPassword
    fun setReWriteNewPassword(newPassword: String) { _reWriteNewPassword.value = newPassword }

    private val _changePasswordInfo = MutableStateFlow<Any?>(null)
    val changePasswordInfo: StateFlow<Any?> get() = _changePasswordInfo

    private val _changePasswordLoading = MutableStateFlow<Boolean>(false)
    val changePasswordLoading: StateFlow<Boolean> get() = _changePasswordLoading

    private val _changePasswordError = MutableStateFlow<String?>(null)
    val changePasswordError: StateFlow<String?> get() = _changePasswordError
    fun setChangePasswordError(newError: String) { _changePasswordError.value = newError }

    val onOldPasswordChange: (String) -> Unit = { password ->
        clearError()
        setOldPassword(password)
    }

    val onNewPasswordChange: (String) -> Unit = { password ->
        clearError()
        if (password.length > MAX_PASSWORD_LENGTH){
            setChangePasswordError(MAX_PASSWORD_LENGTH_MESSAGE)
        }
        else { setNewPassword(password) }
    }

    val onReWriteNewPasswordChange: (String) -> Unit = { password ->
        clearError()
        if (password.length > MAX_PASSWORD_LENGTH){
            setChangePasswordError(MAX_PASSWORD_LENGTH_MESSAGE)
        }
        else { setReWriteNewPassword(password) }
    }

    fun clearError() {
        _changePasswordError.value = null
    }

    val onClick: (String, Int) -> Unit = { token, userId ->
        if (oldPassword.value == "" || newPassword.value == "" || reWriteNewPassword.value == ""){
            setChangePasswordError(MUST_COMPLETE_INPUTS)
        }
        else if (!isValidPassword(newPassword.value)){
            setChangePasswordError(INVALID_PASSWORD)
        }
        else if (newPassword.value != reWriteNewPassword.value){
            setChangePasswordError(PASSWORDS_MUST_MATCH)
        }
        else if (newPassword.value == oldPassword.value){
            setChangePasswordError(PASSWORDS_MUST_BE_DIFFERENT)
        }
        else {
            changePassword(token, userId)
        }
    }

    fun resetVariables(){
        _oldPassword.value = ""
        _newPassword.value = ""
        _reWriteNewPassword.value = ""
        _changePasswordError.value = null
        _changePasswordLoading.value = false
        _changePasswordInfo.value = null
    }

    fun changePassword(token: String, userId: Int) {
        val changePasswordBody = ChangePasswordBody(
            oldPassword.value,
            newPassword.value,
            reWriteNewPassword.value
        )
        authModel.changePassword(
            scope = viewModelScope,
            token = token,
            changePasswordBody = changePasswordBody,
            userId = userId,
            loading = _changePasswordLoading,
            error = _changePasswordError,
            changePasswordInfo = _changePasswordInfo
        )
    }
}