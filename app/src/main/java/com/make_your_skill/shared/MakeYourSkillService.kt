package com.make_your_skill.shared

import com.make_your_skill.helpers.MakeYourSkillRetrofit
import com.make_your_skill.model.RegisterRequest
import retrofit2.Call

class ApiServiceManager {
    private val retrofit = MakeYourSkillRetrofit().getRetrofit()
    private val apiService = retrofit.create(ApiService::class.java)

    fun registerUser(request: RegisterRequest): Call<Void> {
        return apiService.registerUser(request)
    }
}