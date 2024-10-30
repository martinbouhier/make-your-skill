package com.make_your_skill.shared

import com.make_your_skill.model.RegisterRequest
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("auth/register")
    fun registerUser(@Body request: RegisterRequest): Call<Void>

}