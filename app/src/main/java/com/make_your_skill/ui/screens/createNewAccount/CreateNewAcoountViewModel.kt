package com.make_your_skill.ui.screens.createNewAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.make_your_skill.dataClasses.auth.body.RegisterBody
import com.make_your_skill.helpers.functions.formatPhoneNumberToArgentinianFormat
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.auth.AuthService
import com.make_your_skill.helpers.functions.isValidEmail
import com.make_your_skill.helpers.functions.isValidPassword
import com.make_your_skill.models.auth.AuthModel
import com.make_your_skill.ui.navigation.AppRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CreateNewAcoountViewModel @Inject constructor(): ViewModel() {
    private val MUST_COMPLETE_INPUTS = "Must complete inputs"
    private val INVALID_EMAIL = "Email is not valid"
    private val INVALID_PASSWORD = "Password must have a minimum of eight characters, at least one uppercase letter, one lowercase letter, one number and one special character"
    private val PASSWORDS_MUST_MATCH = "Passwords must match"
    private val MAX_EMAIL_LENGTH = 100
    private val MAX_EMAIL_LENGTH_MESSAGE = "Email can't have more than $MAX_EMAIL_LENGTH characters"
    private val MAX_PASSWORD_LENGTH = 40
    private val MAX_PASSWORD_LENGTH_MESSAGE = "Password can't have more than $MAX_PASSWORD_LENGTH characters"
    private val FIRSTNAME_MIN_LENGTH: Int = 3
    private val LASTNAME_MIN_LENGTH: Int = 3
    private val FIRSTNAME_MAX_LENGTH: Int = 15
    private val LASTNAME_MAX_LENGTH: Int = 15
    private val FIRSTSNAME_MIN_LENGTH_ERROR = "Firstname must have at least " + FIRSTNAME_MIN_LENGTH + " characters"
    private val LASTNAME_MIN_LENGTH_ERROR = "Lastname must have at least " + LASTNAME_MIN_LENGTH + " characters"
    private val FIRSTSNAME_MAX_LENGTH_ERROR = "Firstname must have no more than " + FIRSTNAME_MAX_LENGTH + " characters"
    private val LASTNAME_MAX_LENGTH_ERROR = "Lastname must have no more than " + LASTNAME_MAX_LENGTH + " characters"


    val authService: AuthService = RetrofitServiceFactory.makeRetrofitService<AuthService>()
    private val authModel = AuthModel(authService)

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _createNewAccountScreenLoading = MutableStateFlow<Boolean>(false)
    val createNewAccountScreenLoading: StateFlow<Boolean> get() = _createNewAccountScreenLoading

    private val _registerError = MutableStateFlow<String?>(null)
    val registerError: StateFlow<String?> get() = _registerError
    fun setRegisterError(newError: String) { _registerError.value = newError }

    private val _createNewAccountScreenError = MutableStateFlow<String?>(null)
    val createNewAccountScreenError: StateFlow<String?> get() = _createNewAccountScreenError
    fun setCreateNewAccountScreenError(newError: String) { _createNewAccountScreenError.value = newError }

    private val _registerInfo = MutableStateFlow<Any?>(null)
    val registerInfo: StateFlow<Any?> get() = _registerInfo

    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> get() = _email
    fun setEmail(newEmail: String) { _email.value = newEmail }

    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> get() = _password
    fun setPassword(newPassword: String) { _password.value = newPassword }

    private val _reWritePassword = MutableStateFlow<String>("")
    val reWritePassword: StateFlow<String> get() = _reWritePassword
    fun setreWritepassword(newPassword: String) { _reWritePassword.value = newPassword }

    private val _firstname = MutableStateFlow<String>("")
    val firstname: StateFlow<String> get() = _firstname
    fun setFirstname(newFirstname: String) { _firstname.value = newFirstname }

    private val _lastname = MutableStateFlow<String>("")
    val lastname: StateFlow<String> get() = _lastname
    fun setLastname(newLastname: String) { _lastname.value = newLastname }

    private val _dateOfBirth = MutableStateFlow<String>("")
    val dateOfBirth: StateFlow<String> get() = _dateOfBirth
    fun setDateOfBirth(newDate: String) { _dateOfBirth.value = convertStringDateToISO8601(newDate) }

    private val _phoneNumber = MutableStateFlow<String>("")
    val phoneNumber: StateFlow<String> get() = _phoneNumber
    fun setPhoneNumber(newPhoneNumber: String) { _phoneNumber.value = newPhoneNumber }

    private val _errorName = MutableStateFlow<String?>(null)
    val errorName: StateFlow<String?> get() = _errorName
    fun setErrorName(newError: String) { _errorName.value = newError }

    fun resetVariables(){
        _loading.value = false
        _registerError.value = null
        _createNewAccountScreenLoading.value = false
        _registerError.value = null
        _registerInfo.value = null
        _email.value = ""
        _password.value = ""
        _reWritePassword.value = ""
        _firstname.value = ""
        _lastname.value = ""
        _dateOfBirth.value = ""
        _phoneNumber.value = ""
        _defaultDate.value = ""
    }

    val onEmailChange: (String) -> Unit = { newEmail ->
        clearError()
        if (newEmail.length > MAX_EMAIL_LENGTH){
            setCreateNewAccountScreenError(MAX_EMAIL_LENGTH_MESSAGE)
        }
        else { setEmail(newEmail) }
    }

    val onPasswordChange: (String) -> Unit = { newPassword ->
        clearError()
        if (newPassword.length > MAX_PASSWORD_LENGTH){
            setCreateNewAccountScreenError(MAX_PASSWORD_LENGTH_MESSAGE)
        }
        else { setPassword(newPassword) }
    }

    val onReWritePasswordChange: (String) -> Unit = { newPassword ->
        clearError()
        if (newPassword.length > MAX_PASSWORD_LENGTH){
            setCreateNewAccountScreenError(MAX_PASSWORD_LENGTH_MESSAGE)
        }
        else { setreWritepassword(newPassword) }
    }

    val onFirstNameChange: (String) -> Unit = { newFirstname ->
        clearError()
        if (newFirstname.length > FIRSTNAME_MAX_LENGTH){
            setErrorName(FIRSTSNAME_MAX_LENGTH_ERROR)
        }
        else { setFirstname(newFirstname) }
    }

    val onLastNameChange: (String) -> Unit = { newLastname ->
        clearError()
        if (newLastname.length > LASTNAME_MAX_LENGTH){
            setErrorName(LASTNAME_MAX_LENGTH_ERROR)
        }
        else { setLastname(newLastname) }
    }

    val onPhoneNumberChange: (String) -> Unit = { newPhoneNumber ->
        clearError()
        setPhoneNumber(newPhoneNumber)
    }

    fun clearError() {
        _createNewAccountScreenError.value = null
    }

    private val _defaultDate = MutableStateFlow<String>("")
    val defaultDate: StateFlow<String> get() = _defaultDate
    fun setDefaultDate(newDate: String) { _defaultDate.value = newDate }

    //Para la pagina de register (mail y contra)
    val onClick: (NavHostController) -> Unit = {navController ->
        if (email.value == "" || password.value == "" || reWritePassword.value == ""){
            setCreateNewAccountScreenError(MUST_COMPLETE_INPUTS)
        }
        else if (!isValidEmail(email.value)){
            setCreateNewAccountScreenError(INVALID_EMAIL)
        }
        else if (!isValidPassword(password.value)){
            setCreateNewAccountScreenError(INVALID_PASSWORD)
        }
        else if (password.value != reWritePassword.value){
            setCreateNewAccountScreenError(PASSWORDS_MUST_MATCH)
        }
        else {
            navController.navigate(AppRoutes.FIRST_NAME_SCREEN)
        }
    }

    fun onClickName(firstname: String, lastname: String ,navController: NavHostController) {
        if (firstname.isEmpty() || lastname.isEmpty()) {
            setErrorName(MUST_COMPLETE_INPUTS)
        }
        else if (firstname.length < FIRSTNAME_MIN_LENGTH){
            setErrorName(FIRSTSNAME_MIN_LENGTH_ERROR)
        }
        else if (firstname.length > FIRSTNAME_MAX_LENGTH){
            setErrorName(FIRSTSNAME_MAX_LENGTH_ERROR)
        }
        else if (lastname.length < LASTNAME_MIN_LENGTH){
            setErrorName(LASTNAME_MIN_LENGTH_ERROR)
        }
        else if (lastname.length > LASTNAME_MAX_LENGTH){
            setErrorName(LASTNAME_MAX_LENGTH_ERROR)
        }
        else {
            navController.navigate(AppRoutes.CELL_PHONE_SCREEN)
        }
    }

    fun convertStringDateToISO8601(dateString: String): String {
        val inputFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return date.let { outputFormat.format(it) }
    }

    fun register() {
        val registerBody = RegisterBody(
            firstname.value,
            lastname.value,
            email.value,
            phoneNumber.value,
            dateOfBirth.value,
            password.value,
            true
        )
        authModel.register(
            scope = viewModelScope,
            registerBody = registerBody,
            loading = _loading,
            error = _registerError,
            registerInfo = _registerInfo
        )
    }
}