package com.make_your_skill.ui.screens.skill

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.make_your_skill.dataClasses.skills.skillAddedDataClass
import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.ui.navigation.AppRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class SkillsViewModel : ViewModel() {

    val listOfSkills = listOf<skillDataClass>( //Hay que borrarlos despues. Mock Data
        skillDataClass(id =1, skill = "Kotlin", createdAt = "", updatedAt = ""),
        skillDataClass(id =2, skill = "Java", createdAt = "", updatedAt = ""),
        skillDataClass(id =3, skill = "Python", createdAt = "", updatedAt = "")
    )

    private val _showAddPopUp = MutableStateFlow<Boolean>(false)
    val showAddPopUp: StateFlow<Boolean> get() = _showAddPopUp
    fun setShowAddPopUp(newState: Boolean) { _showAddPopUp.value = newState }

    private val _skills = MutableStateFlow<List<skillAddedDataClass>>(emptyList())
    val skills: StateFlow<List<skillAddedDataClass>> get() = _skills
    fun addSkill(newSkill: skillAddedDataClass) { _skills.value + newSkill }

    private val _addedSkill = MutableStateFlow<skillDataClass>(listOfSkills[0])
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
        val skillFound: skillDataClass? = listOfSkills.find { it.id == skillId }
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
        if (addedSkill != null){
            val newSkill: skillAddedDataClass = skillAddedDataClass(
                id = addedSkill.value!!.id,
                skill = addedSkill.value!!.skill,
                selected = true,
                price = addedPrice.value
            )
            addSkill(newSkill)
        }
        setShowAddPopUp(false)
    }

}