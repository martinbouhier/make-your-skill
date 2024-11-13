package com.make_your_skill.ui.screens.forgotPassword

import ForgotPasswordService
import androidx.lifecycle.ViewModel
import com.make_your_skill.dataClasses.forgotPassword.ForgotPasswordDataClass
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.models.forgotPassword.ForgotPasswordModel
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow



class ForgotPasswordViewModel: ViewModel() {
    private val MAX_EMAIL_LENGTH = 100
    private val MAX_EMAIL_LENGTH_MESSAGE = "Email can't have more than $MAX_EMAIL_LENGTH characters"

    fun clearError() {
        _createNewAccountScreenError.value = null
    }

    private val forgotPasswordService: ForgotPasswordService = RetrofitServiceFactory.makeRetrofitService<ForgotPasswordService>()
    private val forgotPasswordModel = ForgotPasswordModel(forgotPasswordService)
    private val _errorAddSkill = MutableStateFlow<String?>(null)

    private val _emailForgotPassword = MutableStateFlow<String?>(null)

    val emailForgotPassword: StateFlow<String?> get() = _emailForgotPassword

    private val _createNewAccountScreenError = MutableStateFlow<String?>(null)
    val createNewAccountScreenError: StateFlow<String?> get() = _createNewAccountScreenError
    fun setCreateNewAccountScreenError(newError: String) { _createNewAccountScreenError.value = newError }

    fun setEmail(newEmail: String) { _emailForgotPassword.value = newEmail }



    fun sendEmailPassword(
        email: ForgotPasswordDataClass,
    ){
        forgotPasswordModel.sendEmail(
            scope = viewModelScope,
            error = _errorAddSkill,
            forgotPasswordBody = email
        )
    }
    val onEmailChange: (String) -> Unit = { newEmail ->
        clearError()
        if (newEmail.length > MAX_EMAIL_LENGTH){
            setCreateNewAccountScreenError(MAX_EMAIL_LENGTH_MESSAGE)
        }
        else { setEmail(newEmail) }
    }
}

