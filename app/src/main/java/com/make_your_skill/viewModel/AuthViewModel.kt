package com.make_your_skill.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> = _isLoggedIn

    fun login() {
        _isLoggedIn.value = true
    }
    fun logout() {
        _isLoggedIn.value = false
    }
}