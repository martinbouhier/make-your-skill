package com.make_your_skill.ui.screens.birthday

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import com.make_your_skill.helpers.validations.isDateBeforeOrEqualToday
import com.make_your_skill.ui.navigation.AppRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BirthdayViewModel : ViewModel(){
    private val MUST_COMPLETE_INPUTS = "Must complete inputs"
    private val BEFORE_OR_EQUAL_TODAY = "Date must be before or equal today"

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error
    fun setError(newError: String) { _error.value = newError }

    fun onClick(
        date: String,
        registerFunc: () -> Unit
    )
    {
        if (date == ""){
            setError(MUST_COMPLETE_INPUTS)
        }
        else if (!isDateBeforeOrEqualToday(date)){
            setError(BEFORE_OR_EQUAL_TODAY)
        }
        else {
            _error.value = null
            registerFunc()
        }
    }
}