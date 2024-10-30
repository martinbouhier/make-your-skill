package com.make_your_skill.helpers

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MakeYourSkillRetrofit {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://3.145.137.163:5000/") // URL de tu backend
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getClient(): OkHttpClient {
        return client
    }

    fun getRetrofit(): Retrofit {
        return retrofit
    }
}