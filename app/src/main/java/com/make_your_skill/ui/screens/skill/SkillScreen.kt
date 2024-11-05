package com.make_your_skill.ui.screens.skill

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.ui.components.CustomButton
import com.make_your_skill.ui.components.addSkillPopUp
import com.make_your_skill.ui.components.ScreenTitleText
import com.make_your_skill.ui.components.skill
import com.make_your_skill.ui.theme.DarkPurple


@Composable
fun SkillsScreen(navController: NavHostController) {
    val listOfSkills = listOf<skillDataClass>( //Hay que borrarlos despues. Mock Data
        skillDataClass(id =1, skill = "Kotlin", createdAt = "", updatedAt = ""),
        skillDataClass(id =2, skill = "Java", createdAt = "", updatedAt = ""),
        skillDataClass(id =3, skill = "Python", createdAt = "", updatedAt = "")
    )

    val skillsViewModel: SkillsViewModel = viewModel()
    val showAddPopUp by skillsViewModel.showAddPopUp.collectAsState()
    val skills by skillsViewModel.skills.collectAsState()//Lista de skills que va a agregar el usuario
    val addedSkill by skillsViewModel.addedSkill.collectAsState()//Skill a agregar
    val addedPrice by skillsViewModel.addedPrice.collectAsState()//Precio del skill a agregar

    val separation = 25.dp
    val BUTTON_TEXT = "CONTINUE 3/4"
    val FIRST_TEXT = "Skills"
    val DIALOG_TITLE = "Add skill"
    val PRICE_LABEL = "Price..."

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
                skillsViewModel.onDismissRequest,
                skillsViewModel.onConfirmation,
                DIALOG_TITLE,
                skillsViewModel.onPriceAddChange,
                PRICE_LABEL,
                addedPrice.toString(),
                listOfSkills,
                skillsViewModel.onSkillAddChange
            )
        }
        Row {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ScreenTitleText(FIRST_TEXT)
                Column {
                    for (skillItem in skills) {
                        skill(skillItem, skillsViewModel.onSkillChange) // Llama al composable SkillItem para cada habilidad
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
                                skillsViewModel.onAdd()
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
                                skillsViewModel.onDelete()
                            },
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = DarkPurple
                    )
                }
            }

        }
        Row {
            CustomButton({skillsViewModel.onClick(navController)},BUTTON_TEXT)
        }
    }
}