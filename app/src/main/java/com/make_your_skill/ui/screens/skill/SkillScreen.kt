package com.make_your_skill.ui.screens.skill

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.dataClasses.skills.skillAddedDataClass
import com.make_your_skill.dataClasses.usersInterestedSkills.body.InterestAddedDataClass
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.popUps.addSkillPopUp
import com.make_your_skill.ui.components.SkillCard
import com.make_your_skill.ui.components.text.ScreenTitleText
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.theme.DarkPurple
import java.text.NumberFormat
import java.util.Locale


@Composable
fun SkillsScreen(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    showContinue: Boolean
) {
    val skillsViewModel: SkillsViewModel = viewModel()
    val listOfSkills by skillsViewModel.listOfSkills.collectAsState()
    val showAddPopUp by skillsViewModel.showAddPopUp.collectAsState()
    val skills by skillsViewModel.skills.collectAsState()//Lista de skills que va a agregar el usuario
    val addedPrice by skillsViewModel.addedPrice.collectAsState()//Precio del skill a agregar
    val error by skillsViewModel.error.collectAsState()
    val loading by skillsViewModel.loading.collectAsState()
    val loadingAddSkill by skillsViewModel.loadingAddSkill.collectAsState()
    val userInfo by singInViewModel.signInInfo.collectAsState()
    val listOfUserSkills by skillsViewModel.listOfUserSkills.collectAsState()
    val priceEdit by skillsViewModel.priceEdit.collectAsState()

    val GAP = 16.dp
    val BUTTON_TEXT = "CONTINUE"
    val FIRST_TEXT = "Skills"
    val DIALOG_TITLE = "Add skill"
    val PRICE_LABEL = "Price..."
    val LOADING_SKILLS = "Loading skills..."
    val LOADING_ADD_SKILLS = "Adding skills"

    LaunchedEffect(userInfo) {
        if (userInfo != null){
            val token = userInfo!!.tokens.token
            skillsViewModel.getAllSkills(token)
            skillsViewModel.getUserSkillByUserId(token,userInfo!!.user.id)
        }
    }

    LaunchedEffect (listOfUserSkills) {
        for (skillIterated in listOfUserSkills){
            val item: skillAddedDataClass = skillAddedDataClass(
                id = skillIterated.skill.id,
                selected = true,
                skill = skillIterated.skill.name,
                price = skillIterated.pricePerHour
            )
            skillsViewModel.addSkill(item)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(GAP),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (showAddPopUp){
            addSkillPopUp(
                skillsViewModel.onDismissRequest,
                {skillsViewModel.onConfirmation(
                    userInfo!!.tokens.token,
                    userInfo!!.user.id
                ) },
                DIALOG_TITLE,
                skillsViewModel.onPriceAddChange,
                PRICE_LABEL,
                addedPrice.toString(),
                listOfSkills,
                skillsViewModel.onSkillAddChange,
                error,
                priceEdit
            )
        }
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ScreenTitleText(FIRST_TEXT)
                LazyColumn(
                    modifier = Modifier
                        .padding(top = GAP * 3)
                        .padding(horizontal = GAP),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val sampleSkills = listOf(
                        skillAddedDataClass(1, true, "Kotlin", 500f),
                        skillAddedDataClass(2, true, "Java", 15000f),
                        skillAddedDataClass(3, true, "Python", 25000f),
                        skillAddedDataClass(4, true, "C++", 35000f),
                    )
                    items(sampleSkills) { skillItem ->
                        SkillCard(skillItem, skillsViewModel.onSkillChange)
                    }
                }
            }
            if (listOfSkills.isNotEmpty()){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(GAP),
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        text = "Add",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { skillsViewModel.onAdd() },
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = DarkPurple
                    )

                    Text(
                        text = "Delete",
                        modifier = Modifier
                            .padding(16.dp) // Aplica un margen de 16dp
                            .clickable {
                                skillsViewModel.onDelete(
                                    userInfo!!.tokens.token,
                                    userInfo!!.user.id
                                )
                            },
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = DarkPurple
                    )
                }
            }
            else if (loading){
                Text(text = LOADING_SKILLS)
            }
            if (error != null){
                Text(
                    text = error.toString(),
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(75.dp))
        }

        Column(
            modifier = Modifier.padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            if (showContinue) {
                CustomButton(
                    { skillsViewModel.onClick(navController) },
                    if (loadingAddSkill) LOADING_ADD_SKILLS else BUTTON_TEXT
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SkillsScreenPreview() {
    SkillsScreen(
        navController = rememberNavController(),
        singInViewModel = SingInViewModel(),
        showContinue = true
    )
}