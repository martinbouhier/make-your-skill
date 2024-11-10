package com.make_your_skill.ui.screens.matchHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.make_your_skill.dataClasses.users.IncreaseVotesBody
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.users.UserService
import com.make_your_skill.models.users.UsersModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MatchHistoryViewModel : ViewModel() {
    val usersService: UserService = RetrofitServiceFactory.makeRetrofitService<UserService>()
    private val usersModel = UsersModel(usersService)

    // Controla el estado de carga de datos
    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    // Informacion de la solicitud
    private val _increaseVotesInfo = MutableStateFlow<Any?>(null)
    val increaseVotesInfo: StateFlow<Any?> get() = _increaseVotesInfo

    // Detecta errores en la solicitud
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error
    fun setError(newError: String) { _error.value = newError }

    // Mensajes de error
    private val MUST_COMPLETE_INPUTS = "Must complete inputs"

    // Gestiona la visibilidad del PopUp de calificar
    private val _showRatePopUp = MutableStateFlow<Boolean>(false)
    val showRatePopUp: StateFlow<Boolean> get() = _showRatePopUp
    fun setRatePopUp(newState: Boolean) { _showRatePopUp.value = newState }

    // Gestiona la adquisición de valores al calificar
    val onConfirmation: (String, Int, Int) -> Unit = { token, userId, vote ->
        if (vote == 0) {
            setError(MUST_COMPLETE_INPUTS)
        }
        else {
            increaseVotes(token = token, userId = userId, vote = vote)
            setRatePopUp(false)
        }
    }

    // Califica al usuario de acuerdo con la información proporcionada
    fun increaseVotes(
        token: String,
        userId: Int,
        vote: Int
    ){
        val increaseVotesBody = IncreaseVotesBody(userId, vote)
        usersModel.increaseVotes(
            scope = viewModelScope,
            loading = _loading,
            error = _error,
            increaseInfo = _increaseVotesInfo,
            token = token,
            increaseVotesBody = increaseVotesBody
        )
    }
}