package com.make_your_skill.ui.screens.matchHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import com.make_your_skill.dataClasses.users.IncreaseVotesBody
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.users.UserService
import com.make_your_skill.models.users.UsersModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MatchHistoryViewModel : ViewModel() {
    val usersService: UserService = RetrofitServiceFactory.makeRetrofitService<UserService>()
    private val usersModel = UsersModel(usersService)

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error
    fun setError(newError: String) { _error.value = newError }

    private val _increaseVotesInfo = MutableStateFlow<Any?>(null)
    val increaseVotesInfo: StateFlow<Any?> get() = _increaseVotesInfo

    private val MUST_COMPLETE_INPUTS = "Must complete inputs"

    private val _showAddPopUp = MutableStateFlow<Boolean>(false)
    val showAddPopUp: StateFlow<Boolean> get() = _showAddPopUp
    fun setShowAddPopUp(newState: Boolean) { _showAddPopUp.value = newState }

    fun onDismissRequest() {
        setShowAddPopUp(false)
    }

    fun onClickRate(){
        setShowAddPopUp(true)

    }

    val onConfirmation: (String, Int, Int) -> Unit = { token, userId, vote ->
        if (vote == 0) {
            setError(MUST_COMPLETE_INPUTS)
        }
        else {
            increaseVotes(token = token, userId = userId, vote = vote)
        }
    }

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