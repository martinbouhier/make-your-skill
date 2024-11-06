package com.make_your_skill.helpers.cookies

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class InMemoryCookieJar : CookieJar {
    private val cookieStore: MutableMap<String, MutableList<Cookie>> = mutableMapOf()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore[url.host] = cookies.toMutableList()
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore[url.host] ?: mutableListOf()
    }

    fun getCookies(): Map<String, List<Cookie>> = cookieStore

    fun getSessionCookie(): String? {
        // Busca la cookie NESTJS_SESSION_ID en todas las cookies almacenadas
        cookieStore.values.flatten().find { it.name == "NESTJS_SESSION_ID" }?.let {
            return "${it.name}=${it.value}"
        }
        return null // Si no se encuentra la cookie
    }
}
