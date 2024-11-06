package com.make_your_skill.helpers.retrofit

import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceFactory {
    inline fun <reified T> makeRetrofitService(): T {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Verás el cuerpo completo de la solicitud y respuesta
        }

        // Crear el cliente OkHttp con el interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }
}