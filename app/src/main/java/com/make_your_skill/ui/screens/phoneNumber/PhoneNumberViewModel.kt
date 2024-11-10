package com.make_your_skill.ui.screens.phoneNumber

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.make_your_skill.helpers.functions.isArgentinianPhoneNumberValid
import com.make_your_skill.ui.navigation.AppRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PhoneNumberViewModel : ViewModel() {
    private val MUST_COMPLETE_INPUTS = "Must complete inputs"
    private val INVALID_PHONE_FORMAT = "Invalid phone format. Please use one of these formats:\n" +
            "• +54 9 1234567890\n" +
            "• 9 1234567890\n" +
            "• +54 1234567890\n" +
            "• 1234567890."


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error
    fun setError(newError: String) { _error.value = newError }

    fun onClick(phoneNumber: String, navController: NavHostController) {
        if (phoneNumber.isEmpty()) {
            setError(MUST_COMPLETE_INPUTS)
        }
        else if (!isArgentinianPhoneNumberValid(phoneNumber)){
            setError(INVALID_PHONE_FORMAT)
        }
        else {
            navController.navigate(AppRoutes.BIRTHDAY_SCREEN)
        }
    }
}