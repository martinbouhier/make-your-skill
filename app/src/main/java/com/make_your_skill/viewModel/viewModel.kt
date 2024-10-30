package com.make_your_skill.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.make_your_skill.model.RegisterRequest
import com.make_your_skill.shared.ApiServiceManager
import kotlinx.coroutines.Dispatchers


class MyViewModel : ViewModel() {
    private val apiServiceManager = ApiServiceManager()

    fun registerUser(request: RegisterRequest) = liveData(Dispatchers.IO) {
        val response = apiServiceManager.registerUser(request).execute()
        if (response.isSuccessful) {
            emit("Registration successful")
        } else {
            emit("Registration failed")
        }
    }
}