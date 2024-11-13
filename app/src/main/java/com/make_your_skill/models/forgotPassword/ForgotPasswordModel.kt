package com.make_your_skill.models.forgotPassword

import ForgotPasswordService
import com.google.gson.Gson
import com.make_your_skill.dataClasses.ErrorResponse
import com.make_your_skill.dataClasses.forgotPassword.ForgotPasswordDataClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


const val ERROR_SENDING_EMAIL = "Error sending email"

class ForgotPasswordModel( private val forgotPasswordService: ForgotPasswordService) {

    fun sendEmail(scope: CoroutineScope,
                  error: MutableStateFlow<String?>,
                  forgotPasswordBody: ForgotPasswordDataClass
    ) {
        scope.launch {
            try {
                val response = forgotPasswordService.emailSendPassword(forgotPasswordBody)
                if (response.isSuccessful) {
                    error.value = null
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_SENDING_EMAIL
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_SENDING_EMAIL}
        }



    }
}

