package com.make_your_skill.helpers.retrofit.auth

import com.make_your_skill.models.auth.Auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAuthService(
    private val authRetrofit: AuthRetrofit
) {
    suspend fun login(): Auth? = withContext(Dispatchers.IO){
        authRetrofit.login()
    }
}