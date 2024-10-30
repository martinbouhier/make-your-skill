package com.make_your_skill.ui.screens.createNewAccount

import androidx.lifecycle.ViewModel
import com.make_your_skill.model.RegisterRequest
import com.make_your_skill.shared.ApiServiceManager

class CreateNewAcoountViewModel: ViewModel() {
    private val apiService = ApiServiceManager()

     fun register(firstname: String, lastname: String, email: String, dateOfBirth: String, password: String, isActive: Boolean) {
         val registerRequest = RegisterRequest(
             firstname = "John",
             lastname = "Doe",
             email = "fake@email.com",
             dateOfBirth = "2000-01-01",
             password = "password",
             isActive = true
         )

         apiService.registerUser(registerRequest)
    }
}