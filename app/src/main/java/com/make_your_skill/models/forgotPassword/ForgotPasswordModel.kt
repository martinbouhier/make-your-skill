package com.make_your_skill.models.forgotPassword

import ForgotPasswordService
import com.google.gson.Gson
import com.make_your_skill.dataClasses.ErrorResponse
import com.make_your_skill.dataClasses.forgotPassword.ForgotPasswordDataClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response


const val ERROR_SENDING_EMAIL = "Error sending email"

class ForgotPasswordModel( private val forgotPasswordService: ForgotPasswordService) {

    suspend fun sendEmail(
        scope: CoroutineScope,
        error: MutableStateFlow<String?>,
        forgotPasswordBody: ForgotPasswordDataClass
    ): Response<Void> {
        return try {
            val response = forgotPasswordService.emailSendPassword(forgotPasswordBody)
            if (response.isSuccessful) {
                error.value = null
            } else {
                error.value = ERROR_SENDING_EMAIL
            }
            response
        } catch (e: Exception) {
            error.value = ERROR_SENDING_EMAIL
            Response.error(500, ResponseBody.create(null, ""))
        }
    }
}

