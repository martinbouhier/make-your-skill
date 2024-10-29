package com.make_your_skill.ui.screens.skill

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
import com.make_your_skill.ui.components.addSkillPopUp
import com.make_your_skill.ui.components.customText
import com.make_your_skill.ui.components.skill
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.theme.DarkPurple


@Composable
fun SkillsScreen(navController: NavHostController) {
    var showAddPopUp by remember { mutableStateOf(false) } //Si muestro el popup o no
    var skills by remember {// Lista de skills
        mutableStateOf(
            listOf( //Hay que borrarlos despues
                skillDataClass(id =1,selected = true, skill = "Kotlin", price = 450f),
                skillDataClass(id =2,selected = false, skill = "Java", price = 400f),
                skillDataClass(id =3,selected = false, skill = "Python", price = 10f)
            )
        )
    }
    val separation = 25.dp
    val BUTTON_TEXT = "CONTINUE 3/4"
    val FIRST_TEXT = "Skills"
    val DIALOG_TITLE = "Add skill"

    val onClick = {
        if (!skills.isEmpty()){
            val selectedSkills = skills.filter { it.selected } //Filtramos los skills tickeated
            if (!selectedSkills.isEmpty()){
                navController.navigate(AppRoutes.BIRTHDAY_SCREEN)
            }
        }
    }

    val onSkillChange: (skillDataClass) -> Unit = { updatedSkill ->
        skills = skills.map { if (it.id == updatedSkill.id) updatedSkill else it }
    }

    val onAdd = {
        showAddPopUp = true
    }

    val onDelete = {
        val unSelectedSkills = skills.filter { !it.selected } //Filtramos los skills tickeated
        skills = unSelectedSkills
    }

    val onDismissRequest = {
        showAddPopUp = false
    }

    val onConfirmation = {

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
            addSkillPopUp(
                onDismissRequest,
                onConfirmation,
                DIALOG_TITLE
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
                customText(FIRST_TEXT)
                Column {
                    for (skillItem in skills) {
                        skill(skillItem, onSkillChange) // Llama al composable SkillItem para cada habilidad
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