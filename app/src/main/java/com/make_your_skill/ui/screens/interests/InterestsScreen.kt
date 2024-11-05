package com.make_your_skill.ui.screens.interests

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.make_your_skill.ui.components.BackButton
import com.make_your_skill.ui.components.CustomButton
import com.make_your_skill.ui.components.addInterestsPopUp
import com.make_your_skill.ui.components.ScreenTitleText
import com.make_your_skill.ui.components.interest
import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.ui.theme.DarkPurple

@Composable
fun InterestedSkillsScreen(navController: NavHostController) {
    val listOfSkills = listOf<skillDataClass>( //Hay que borrarlos despues. Mock Data
        skillDataClass(id =1, skill = "Kotlin", createdAt = "", updatedAt = ""),
        skillDataClass(id =2, skill = "Java", createdAt = "", updatedAt = ""),
        skillDataClass(id =3, skill = "Python", createdAt = "", updatedAt = "")
    )

    var showAddPopUp by remember { mutableStateOf(false) } //Si muestro el popup o no
    var interests by remember { mutableStateOf<List<interestAddedDataClass>>(emptyList()) } //Lista de skills que va a agregar el usuario
    var addedInterestedSkill by remember { mutableStateOf<skillDataClass?>(listOfSkills[0]) }//Skill a agregar
    val separation = 25.dp
    val BUTTON_TEXT = "CONTINUE 4/4"
    val FIRST_TEXT = "Interests"
    val DIALOG_TITLE = "Add skill"

    //Funcion para cuando hago click en continue
    val onClick = {
        if (!interests.isEmpty()){
            val selectedSkills = interests.filter { it.selected } //Filtramos los skills tickeated
            if (!selectedSkills.isEmpty()){
                //navController.navigate(AppRoutes.BIRTHDAY_SCREEN)
            }
        }
    }

    //Para cuando se hace un cambio en el objeto skill (principalmente si esta selected o no)
    val onSkillChange: (interestAddedDataClass) -> Unit = { updatedSkill ->
        interests = interests.map { if (it.id == updatedSkill.id) updatedSkill else it }
    }

    //Cuando click en add skill
    val onAdd = {
        showAddPopUp = true
    }

    //Cuando hago click en delete y borro un skill
    val onDelete = {
        val unSelectedInterests = interests.filter { !it.selected } //Filtramos los skills tickeated
        interests = unSelectedInterests
    }

    //Cerramos popup
    val onDismissRequest = {
        showAddPopUp = false
    }

    //Confirmo que agrego skill en el popup
    val onConfirmation = {
        if (addedInterestedSkill != null){
            val newSkill: interestAddedDataClass = interestAddedDataClass(
                id = addedInterestedSkill!!.id,
                skill = addedInterestedSkill!!.skill,
                selected = true
            )
            interests = interests + newSkill
        }
        showAddPopUp = false
    }

    //Cuando cambia el skill que voy a agregar
    val onSkillAddChange: (Int) -> Unit = { skillId ->
        val skillFound: skillDataClass? = listOfSkills.find { it.id == skillId }
        addedInterestedSkill = skillFound ?: null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(separation),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (showAddPopUp == true){
            addInterestsPopUp(
                onDismissRequest,
                onConfirmation,
                DIALOG_TITLE,
                listOfSkills,
                onSkillAddChange
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BackButton(navController, Color.Gray)
        }
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ScreenTitleText(FIRST_TEXT)
                Column {
                    for (skillItem in interests) {
                        interest(skillItem, onSkillChange) // Llama al composable SkillItem para cada habilidad
                    }
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(0.7f),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Add",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                onAdd()
                            }, // Aplica un margen de 16dp
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = DarkPurple
                    )
                    Text(
                        text = "Delete",
                        modifier = Modifier
                            .padding(16.dp) // Aplica un margen de 16dp
                            .clickable {
                                onDelete()
                            },
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = DarkPurple
                    )
                }
            }

        }
        Row {
            CustomButton(onClick,BUTTON_TEXT)
        }
    }
}

