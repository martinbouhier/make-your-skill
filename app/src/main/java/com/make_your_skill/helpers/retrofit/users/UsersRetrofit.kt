package com.make_your_skill.helpers.retrofit.users

import com.make_your_skill.helpers.Credentials
import com.make_your_skill.models.user.User

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request

class UsersRetrofit: UsersServiceInterface {

    // Configura un interceptor que añade el header Authorization
    private val authInterceptor = Interceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${Credentials.TOKEN}")
            .build()
        chain.proceed(newRequest)
    }

    // Configura el cliente OkHttp con el interceptor
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    //Instanciamos retrofit
    private val retrofit: Retrofit = Retrofit
        .Builder()
        .client(okHttpClient) // Configura Retrofit con el cliente OkHttp personalizado
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Credentials.BASE_URL)
        .build()

    private val api = retrofit.create(UsersInterface::class.java)

    override suspend fun getAll(): List<User>? {
        val response = api.getAll()

        return if (response.isSuccessful){
            val result = response.body()?.toModel()
            result
        }
        else {
            emptyList()
        }
    }

    override suspend fun findOne(): User? {
        val response = api.findOne()

        return if (response.isSuccessful){
            val result = response.body()?.toModel()
            result
        }
        else {
            null
        }
    }

    override suspend fun findById(): User? {
        val response = api.findOne()

        return if (response.isSuccessful){
            val result = response.body()?.toModel()
            result
        }
        else {
            null
        }
    }
}