package com.make_your_skill.ui.screens.singIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.make_your_skill.helpers.retrofit.auth.AuthServiceInterface
import com.make_your_skill.helpers.retrofit.auth.GetAuthService
import com.make_your_skill.models.auth.Auth
import kotlinx.coroutines.launch


class SingInViewModel(
    private val getAuthService: GetAuthService
) : ViewModel() {

    fun login(email: String, password: String){
        viewModelScope.launch {
            val loginData: Auth? = getAuthService.login()
        }
    }

    companion object {

        fun provideFactory(getAuthService: GetAuthService): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel>create(modelClass: Class<T>): T {
                return SingInViewModel(getAuthService) as T
            }
        }
    }
}