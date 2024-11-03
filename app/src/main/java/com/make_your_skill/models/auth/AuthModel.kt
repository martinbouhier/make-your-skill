package com.make_your_skill.models.auth

import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import com.make_your_skill.helpers.retrofit.auth.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthModel(private val authService: AuthService) {
    private val ERROR_LOGIN_IN = "Error logging in"

    fun signIn(
        scope: CoroutineScope,
        signInBody: SignInBody,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        signInInfo: MutableStateFlow<SignInDto?>
    ) {
        scope.launch {
            loading.value = true
            try {
                val response = authService.login(signInBody)
                signInInfo.value = response
                error.value = null
            } catch (e: Exception) {
                error.value = ERROR_LOGIN_IN
            } finally {
                loading.value = false
            }
        }
    }
}