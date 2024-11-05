package com.make_your_skill.ui.screens.firstName

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.make_your_skill.ui.navigation.AppRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FirstNameViewModel @Inject constructor(): ViewModel() {
    private val MUST_COMPLETE_INPUTS = "Must complete inputs"
    private val FIRSTNAME_MIN_LENGTH: Int = 3
    private val LASTNAME_MIN_LENGTH: Int = 3
    private val FIRSTNAME_MAX_LENGTH: Int = 15
    private val LASTNAME_MAX_LENGTH: Int = 15
    private val FIRSTSNAME_MIN_LENGTH_ERROR = "Firstname must have at least " + FIRSTNAME_MIN_LENGTH + " characters"
    private val LASTNAME_MIN_LENGTH_ERROR = "Lastname must have at least " + LASTNAME_MIN_LENGTH + " characters"
    private val FIRSTSNAME_MAX_LENGTH_ERROR = "Firstname must have at least " + FIRSTNAME_MAX_LENGTH + " characters"
    private val LASTNAME_MAX_LENGTH_ERROR = "Lastname must have at least " + LASTNAME_MAX_LENGTH + " characters"


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error
    fun setError(newError: String) { _error.value = newError }

    fun onClick(firstname: String, lastname: String ,navController: NavHostController) {
        if (firstname.isEmpty() || lastname.isEmpty()) {
            setError(MUST_COMPLETE_INPUTS)
        }
        else if (firstname.length < FIRSTNAME_MIN_LENGTH){
            setError(FIRSTSNAME_MIN_LENGTH_ERROR)
        }
        else if (firstname.length > FIRSTNAME_MAX_LENGTH){
            setError(FIRSTSNAME_MAX_LENGTH_ERROR)
        }
        else if (lastname.length < LASTNAME_MIN_LENGTH){
            setError(LASTNAME_MIN_LENGTH_ERROR)
        }
        else if (lastname.length > LASTNAME_MAX_LENGTH){
            setError(LASTNAME_MAX_LENGTH_ERROR)
        }
        else {
            navController.navigate(AppRoutes.BIRTHDAY_SCREEN)
        }
    }
}