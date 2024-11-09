package com.make_your_skill.models.auth

import android.util.Log
import com.google.gson.Gson
import com.make_your_skill.dataClasses.ErrorResponse
import com.make_your_skill.dataClasses.auth.body.ChangePasswordBody
import com.make_your_skill.dataClasses.auth.body.RegisterBody
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.helpers.retrofit.Credentials
import com.make_your_skill.helpers.retrofit.auth.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.Cookie
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

class AuthModel(private val authService: AuthService) {
    private val ERROR_LOGIN_IN = "Error logging in"
    private val ERROR_IN_REGISTER = "Error while registering"
    private val ERROR_CHANGING_PASSWORD = "Error while changing password"

    fun signIn(
        scope: CoroutineScope,
        signInBody: SignInBody,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        signInInfo: MutableStateFlow<SignInDto?>,
        isLoggedIn: MutableStateFlow<Boolean>,
        cookieJar: InMemoryCookieJar
    ) {
        scope.launch {
            loading.value = true
            try {
                val response = authService.login(signInBody)
                if (response.isSuccessful){
                    response.headers()["Set-Cookie"]?.let { cookies ->
                        val sessionCookie = cookies.split(";").find { it.contains("NESTJS_SESSION_ID") }
                        sessionCookie?.let { cookie ->
                            val cookieValue = cookie.trim()
                            // Guardamos la cookie en el cookieJar
                            val httpUrl = Credentials.BASE_URL.toHttpUrlOrNull()!!
                            val parsedCookie = Cookie.parse(httpUrl, cookieValue)
                            if (parsedCookie != null) {
                                cookieJar.saveFromResponse(httpUrl, listOf(parsedCookie))
                            }
                        }
                    }

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

    fun changePassword(
        scope: CoroutineScope,
        changePasswordBody: ChangePasswordBody,
        userId: String,
        loading: MutableStateFlow<Boolean>,
        error: MutableStateFlow<String?>,
        changePasswordInfo: MutableStateFlow<Any?>
    ) {
        scope.launch {
            loading.value = true
            try {
                val response = authService.changePassword(userId,changePasswordBody)
                if (response.isSuccessful){
                    changePasswordInfo.value = response.body()
                    error.value = null
                }
                else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java).message
                    } ?: ERROR_CHANGING_PASSWORD
                    error.value = errorMessage
                }
            } catch (e: Exception) {
                error.value = ERROR_CHANGING_PASSWORD
            } finally {
                loading.value = false
            }
        }
    }

}