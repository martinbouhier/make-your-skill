package com.make_your_skill

import android.app.Application
import android.content.Context
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MakeYourSkillApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
    }
}