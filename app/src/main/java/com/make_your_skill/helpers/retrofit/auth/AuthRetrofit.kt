package com.make_your_skill.helpers.retrofit.auth

import com.make_your_skill.helpers.Credentials
import com.make_your_skill.models.auth.Auth
import com.make_your_skill.models.user.User

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

class AuthRetrofit: AuthServiceInterface {

    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    //Instanciamos retrofit
    private val retrofit: Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(Credentials.BASE_URL)
        .build()

    private val api = retrofit.create(AuthInterface::class.java)

    override suspend fun login(): Auth? {
        val response = api.login()

        return if (response.isSuccessful){
            var result = response.body()
            if (result != null) {
                result = Auth(user = result.user, tokens = result.tokens)
            }
            result
        }
        else {
            null
        }
    }
}