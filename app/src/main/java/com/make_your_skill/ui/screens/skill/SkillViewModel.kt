package com.make_your_skill.ui.screens.skill

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.make_your_skill.dataClasses.skills.skillAddedDataClass
import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.dataClasses.usersSkills.body.AddUserSkill
import com.make_your_skill.dataClasses.usersSkills.body.DeleteUserSkill
import com.make_your_skill.dataClasses.usersSkills.body.GetUserSkillByUserId
import com.make_your_skill.helpers.retrofit.RetrofitServiceFactory
import com.make_your_skill.helpers.retrofit.skills.SkillsService
import com.make_your_skill.helpers.retrofit.usersSkills.UsersSkillsService
import com.make_your_skill.models.skills.SkillsModel
import com.make_your_skill.models.usersSkills.UsersSkillsModel
import com.make_your_skill.ui.navigation.AppRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SkillsViewModel @Inject constructor() : ViewModel() {
    private val MAX_PRICE_LIMIT = 99999.99f
    private val PRICE_MAX_LIMIT_ERROR = "The maximum allowed value is $MAX_PRICE_LIMIT"


    val skillsService: SkillsService = RetrofitServiceFactory.makeRetrofitService<SkillsService>()
    private val skillsModel = SkillsModel(skillsService)

    val usersSkillsService: UsersSkillsService = RetrofitServiceFactory.makeRetrofitService<UsersSkillsService>()
    private val usersSkillModel = UsersSkillsModel(usersSkillsService)

    private val _listOfSkills = MutableStateFlow<List<skillDataClass>>(emptyList())
    val listOfSkills: StateFlow<List<skillDataClass>> get() = _listOfSkills

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _loadingAddSkill = MutableStateFlow<Boolean>(false)
    val loadingAddSkill: StateFlow<Boolean> get() = _loadingAddSkill

    private val _loadingDeleteSkill = MutableStateFlow<Boolean>(false)
    val loadingDeleteSkill: StateFlow<Boolean> get() = _loadingDeleteSkill

    private val _loadingUpdateSkill = MutableStateFlow<Boolean>(false)
    val loadingUpdateSkill: StateFlow<Boolean> get() = _loadingUpdateSkill

    private val _priceEdit = MutableStateFlow<Boolean>(false)
    val priceEdit: StateFlow<Boolean> get() = _priceEdit
    fun setPriceEdit(newState: Boolean) { _priceEdit.value = newState }

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error
    fun setError(newError: String) { _error.value = newError }

    private val _errorAddSkill = MutableStateFlow<String?>(null)
    val errorAddSkill: StateFlow<String?> get() = _errorAddSkill
    fun setErrorAddSkill(newError: String) { _errorAddSkill.value = newError }

    private val _errorDeleteSkill = MutableStateFlow<String?>(null)
    val errorDeleteSkill: StateFlow<String?> get() = _errorDeleteSkill
    fun setErrorDeleteSkill(newError: String) { _errorDeleteSkill.value = newError }

    private val _errorUpdateSkill = MutableStateFlow<String?>(null)
    val errorUpdateSkill: StateFlow<String?> get() = _errorUpdateSkill
    fun setErrorUpdateSkill(newError: String) { _errorUpdateSkill.value = newError }

    private val _showAddPopUp = MutableStateFlow<Boolean>(false)
    val showAddPopUp: StateFlow<Boolean> get() = _showAddPopUp
    fun setShowAddPopUp(newState: Boolean) { _showAddPopUp.value = newState }

    private val _skills = MutableStateFlow<List<skillAddedDataClass>>(emptyList())
    val skills: StateFlow<List<skillAddedDataClass>> get() = _skills
    fun addSkill(newSkill: skillAddedDataClass) {
        if (findSkillById(newSkill.id) == null){
            _skills.value += newSkill // Asignar la nueva lista
        }
    }

    fun findSkillById(id: Int): skillAddedDataClass? {
        return _skills.value.find { it.id == id }
    }

    private val _addedSkill = MutableStateFlow<skillDataClass?>(null)
    val addedSkill: StateFlow<skillDataClass?> get() = _addedSkill
    fun setAddedSkill(skill: skillDataClass) { _addedSkill.value = skill }

    private val _addedPrice = MutableStateFlow<String>("")
    val addedPrice: StateFlow<String> get() = _addedPrice
    fun setAddedPrice(newPrice: String) { _addedPrice.value = newPrice }

    private val _listOfUserSkills = MutableStateFlow<List<GetUserSkillByUserId>>(emptyList())
    val listOfUserSkills: StateFlow<List<GetUserSkillByUserId>> get() = _listOfUserSkills

    private val _loadingUserSkills = MutableStateFlow<Boolean>(false)
    val loadingUserSkills: StateFlow<Boolean> get() = _loadingUserSkills

    private val _errorUserSkills = MutableStateFlow<String?>(null)
    val errorUserSkills: StateFlow<String?> get() = _errorUserSkills
    fun setErrorUserSkills(newError: String) { _errorUserSkills.value = newError }


    //Para cuando se hace un cambio en el objeto skill (principalmente si esta selected o no)
    val onSkillChange: (skillAddedDataClass) -> Unit = { updatedSkill ->
        _skills.value = _skills.value.map { if (it.id == updatedSkill.id) updatedSkill else it }
    }

    //Cuando hago click en delete y borro un skill
    val onDelete: (String, Int) -> Unit = { token, userId ->
        val selectedSkills = _skills.value.filter { it.selected } // Filtramos los skills seleccionados
        val unselectedSkills = _skills.value.filter { !it.selected } // Filtramos los skills no seleccionados

        //en el back borramos las seleccionadas
        for (selectedSkill in selectedSkills){
            deleteUserSkillBack(selectedSkill,token,userId)
        }

        //dejamos para el front todas las que no fueron seleccionadas
        _skills.value = unselectedSkills // Actualizamos la lista sin los skills seleccionados
    }

    //Cuando click en add skill
    val onAdd = {
        setAddedSkill(listOfSkills.value[0])
        setAddedPrice("")
        setShowAddPopUp(true)
    }

    val onEdit = {
        setPriceEdit(true)
        setAddedPrice(addedPrice.value) // Poner precio actual en lugar de ""
        setShowAddPopUp(true)
    }

    //Cerramos popup
    val onDismissRequest = {
        setShowAddPopUp(false)
        setPriceEdit(false)
    }

    //Cuando cambia de precio el skill que voy a agregar
    val onPriceAddChange: (String) -> Unit = { price ->
        val filteredPrice = price.filter { it.isDigit() || it == '.' }
        val decimalIndex = filteredPrice.indexOf('.')

        val validatedPrice = if (decimalIndex != -1) {
            filteredPrice.substring(0, decimalIndex + 1) +
                    filteredPrice.substring(decimalIndex + 1).take(2)
        } else {
            filteredPrice
        }
        val finalPrice = validatedPrice.toFloatOrNull() ?: 0f
        if (finalPrice <= MAX_PRICE_LIMIT) {
            setAddedPrice(validatedPrice)
            setError("")
        } else {
            setAddedPrice(price)
            setError(PRICE_MAX_LIMIT_ERROR)
        }
    }


    // Cuando cambia el skill que voy a agregar
    val onSkillAddChange: (Int) -> Unit = { skillId ->
        val skillFound: skillDataClass? = listOfSkills.value.find { it.id == skillId }
        setAddedSkill(skillFound!!) // Actualizamos el estado con el skill encontrado
    }

    //Funcion para cuando hago click en continue
    val onClick: (NavController) -> Unit = { navController ->
        navController.navigate(AppRoutes.INTERESTS_SCREEN + "?showContinue=true")
    }

    //Confirmo que agrego skill en el popup
    val onConfirmation: (String, Int) -> Unit = { token, userId ->
        val finalPrice = addedPrice.value.toFloatOrNull() ?: 0f
        if (finalPrice <= MAX_PRICE_LIMIT && addedSkill.value != null){

            if(!priceEdit.value){
               val newSkill: skillAddedDataClass = skillAddedDataClass(
                id = addedSkill.value!!.id,
                skill = addedSkill.value!!.name,
                selected = true,
                price = finalPrice
            )
            //agrego el skill en el back
            addSkillBack(newSkill,token,userId)

            //Agrego el skill en el front
            addSkill(newSkill)
            }else{
                val editSkill: skillAddedDataClass = skillAddedDataClass(
                    id = addedSkill.value!!.id,
                    skill = addedSkill.value!!.name,
                    selected = true,
                    price = addedPrice.value.toFloat()
                )
                updateSkillBack(editSkill, token, userId)
                onSkillChange(editSkill)
            }
            setPriceEdit(false)      
            setShowAddPopUp(false)
        }
    }

    fun addSkillBack(
        skill: skillAddedDataClass,
        token: String,
        userId: Int
    ){
        usersSkillModel.addUserSkill(
            scope = viewModelScope,
            loading = _loadingAddSkill,
            error = _errorAddSkill,
            addUserSkillBody = AddUserSkill(
                userId = userId,
                skillId = skill.id,
                pricePerHour = skill.price!!
            ),
            token = token
        )
    }

    fun deleteUserSkillBack(
        skill: skillAddedDataClass,
        token: String,
        userId: Int
    ){
        usersSkillModel.deleteUserSkill(
            scope = viewModelScope,
            loading = _loadingDeleteSkill,
            error = _errorDeleteSkill,
            deleteUserSkill = DeleteUserSkill(
                userId = userId,
                skillId = skill.id
            ),
            token = token
        )
    }

    fun updateSkillBack(
        skill: skillAddedDataClass,
        token: String,
        userId: Int
    ) {
        usersSkillModel.updateUserSkill(
            scope = viewModelScope,
            loading = _loadingUpdateSkill,
            error = _errorUpdateSkill,
            editUserSkill = AddUserSkill(
                userId = userId,
                skillId = skill.id,
                pricePerHour = skill.price!!
            ),
            token = token,
        )
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

    fun getUserSkillByUserId(
        token: String,
        userId: Int
    ){
        usersSkillModel.getUserSkillsByUserId(
            scope = viewModelScope,
            loading = _loadingUserSkills,
            error = _errorUserSkills,
            listOfUserSkills = _listOfUserSkills,
            userId = userId,
            token = token
        )
    }
}