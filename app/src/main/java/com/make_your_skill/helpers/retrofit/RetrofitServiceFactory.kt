package com.make_your_skill.helpers.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceFactory {
    inline fun <reified T> makeRetrofitService(): T {
        return Retrofit.Builder()
            .baseUrl("http://3.145.137.163:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }
}