package com.make_your_skill.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.make_your_skill.shared.MakeYourSkillService
import com.make_your_skill.models.MakeYourSkillApiModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MakeYourSkillViewModel : ViewModel() {

    private val service = MakeYourSkillService()

    private val _skills = MutableStateFlow<List<MakeYourSkillApiModel>?>(null)
    val skills: StateFlow<List<MakeYourSkillApiModel>?> get() = _skills

    fun loadSkills() {
        viewModelScope.launch {
            _skills.value = service.getSkills()
        }
    }
}