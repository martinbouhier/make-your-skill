package com.make_your_skill.ui.screens.skill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.make_your_skill.dataClasses.auth.body.SignInBody
import com.make_your_skill.dataClasses.skills.skillAddedDataClass
import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.skills.SkillsService
import com.make_your_skill.helpers.validations.isValidEmail
import com.make_your_skill.models.skills.SkillsModel
import com.make_your_skill.ui.navigation.AppRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SkillsViewModel @Inject constructor() : ViewModel() {
    val skillsService: SkillsService = RetrofitServiceFactory.makeRetrofitService<SkillsService>()
    private val skillsModel = SkillsModel(skillsService)

    private val _listOfSkills = MutableStateFlow<List<skillDataClass>>(emptyList())
    val listOfSkills: StateFlow<List<skillDataClass>> get() = _listOfSkills

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error
    fun setError(newError: String) { _error.value = newError }

    private val _showAddPopUp = MutableStateFlow<Boolean>(false)
    val showAddPopUp: StateFlow<Boolean> get() = _showAddPopUp
    fun setShowAddPopUp(newState: Boolean) { _showAddPopUp.value = newState }

    private val _skills = MutableStateFlow<List<skillAddedDataClass>>(emptyList())
    val skills: StateFlow<List<skillAddedDataClass>> get() = _skills
    fun addSkill(newSkill: skillAddedDataClass) {
        _skills.value = _skills.value + newSkill // Asignar la nueva lista
    }

    private val _addedSkill = MutableStateFlow<skillDataClass>(skillDataClass(id = 0, skill = "", createdAt = "", updatedAt = ""))
    val addedSkill: StateFlow<skillDataClass> get() = _addedSkill
    fun setAddedSkill(skill: skillDataClass) { _addedSkill.value = skill }

    private val _addedPrice = MutableStateFlow<Float>(0.0f)
    val addedPrice: StateFlow<Float> get() = _addedPrice
    fun setAddedPrice(newPrice: Float) { _addedPrice.value = newPrice }

    //Para cuando se hace un cambio en el objeto skill (principalmente si esta selected o no)
    val onSkillChange: (skillAddedDataClass) -> Unit = { updatedSkill ->
        _skills.value = _skills.value.map { if (it.id == updatedSkill.id) updatedSkill else it }
    }

    //Cuando hago click en delete y borro un skill
    val onDelete = {
        val unselectedSkills = _skills.value.filter { !it.selected } // Filtramos los skills no seleccionados
        _skills.value = unselectedSkills // Actualizamos la lista sin los skills seleccionados
    }

    //Cuando click en add skill
    val onAdd = {
        setShowAddPopUp(true)
    }

    //Cerramos popup
    val onDismissRequest = {
        setShowAddPopUp(false)
    }

    //Cuando cambia de precio el skill que voy a agregar
    val onPriceAddChange: (String) -> Unit = { price ->
        setAddedPrice(price.toFloat())
    }

    // Cuando cambia el skill que voy a agregar
    val onSkillAddChange: (Int) -> Unit = { skillId ->
        val skillFound: skillDataClass? = listOfSkills.value.find { it.id == skillId }
        setAddedSkill(skillFound!!) // Actualizamos el estado con el skill encontrado
    }

    //Funcion para cuando hago click en continue
    val onClick: (NavController) -> Unit = { navController ->
        val currentSkills = _skills.value // Obtenemos la lista actual de skills
        if (currentSkills.isNotEmpty()) {
            val selectedSkills = currentSkills.filter { it.selected } // Filtramos los skills seleccionados
            if (selectedSkills.isNotEmpty()) {
                navController.navigate(AppRoutes.INTERESTS_SCREEN)
            }
        }
    }

    //Confirmo que agrego skill en el popup
    val onConfirmation = {
        val newSkill: skillAddedDataClass = skillAddedDataClass(
            id = addedSkill.value.id,
            skill = addedSkill.value.skill,
            selected = true,
            price = addedPrice.value
        )
        addSkill(newSkill)
        setShowAddPopUp(false)
    }

    val getAllSkills: (String) -> Unit = { token ->
        skillsModel.getAllSkills(
            scope = viewModelScope,
            loading = _loading,
            error = _error,
            listOfSkills = _listOfSkills,
            token = token
        )
    }
}