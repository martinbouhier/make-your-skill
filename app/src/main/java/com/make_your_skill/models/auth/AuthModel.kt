package com.make_your_skill.models.auth

import android.util.Log
import com.google.gson.Gson
import com.make_your_skill.dataClasses.ErrorResponse
import com.make_your_skill.dataClasses.auth.body.RegisterBody
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import com.make_your_skill.helpers.retrofit.auth.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthModel(private val authService: AuthService) {
    private val ERROR_LOGIN_IN = "Error logging in"
    private val ERROR_IN_REGISTER = "Error while registering"

    fun signIn(
        scope: CoroutineScope,
        signInBody: SignInBody,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        signInInfo: MutableStateFlow<SignInDto?>,
        isLoggedIn: MutableStateFlow<Boolean>
    ) {
        scope.launch {
            loading.value = true
            try {
                val response = authService.login(signInBody)
                if (response.isSuccessful){
                    signInInfo.value = response.body()
                    error.value = null
                    isLoggedIn.value = true
                }
                else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_LOGIN_IN
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_LOGIN_IN
            } finally {
                loading.value = false
            }
        }
    }


    fun register(
        scope: CoroutineScope,
        registerBody: RegisterBody,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        registerInfo: MutableStateFlow<Any?>
    ) {
        scope.launch {
            loading.value = true
            try {
                val response = authService.register(registerBody)
                if (response.isSuccessful){
                    registerInfo.value = response.body()
                    error.value = null
                }
                else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_IN_REGISTER
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_IN_REGISTER
            } finally {
                loading.value = false
            }
        }
    }

}