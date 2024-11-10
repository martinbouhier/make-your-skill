package com.make_your_skill.ui.screens.interests

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.dataClasses.skills.skillAddedDataClass
import com.make_your_skill.dataClasses.usersInterestedSkills.body.InterestAddedDataClass
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.interest
import com.make_your_skill.ui.components.popUps.addInterestsPopUp
import com.make_your_skill.ui.components.skill
import com.make_your_skill.ui.components.text.ScreenTitleText
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.theme.DarkPurple

@Composable
fun InterestedSkillsScreen(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    showContinue: Boolean
) {
    val interestsViewModel: InterestsViewModel = viewModel()
    val listOfSkills by interestsViewModel.listOfSkills.collectAsState()
    val showAddPopUp by interestsViewModel.showAddPopUp.collectAsState()
    val skills by interestsViewModel.skills.collectAsState()
    val error by interestsViewModel.error.collectAsState()
    val loading by interestsViewModel.loading.collectAsState()
    val loadingAddSkill by interestsViewModel.loadingAddSkill.collectAsState()
    val userInfo by singInViewModel.signInInfo.collectAsState()
    val listOfUserSkills by interestsViewModel.listOfUserSkills.collectAsState()

    val separation = 25.dp
    val BUTTON_TEXT = "CONTINUE"
    val FIRST_TEXT = "Interests"
    val DIALOG_TITLE = "Add interet"
    val LOADING_SKILLS = "Loading skills..."
    val LOADING_ADD_INTEREST = "Adding interest"

    LaunchedEffect(userInfo) {
        if (userInfo != null){
            val token = userInfo!!.tokens.token
            interestsViewModel.getAllSkills(token)
            interestsViewModel.getUserSkillByUserId(token,userInfo!!.user.id)
        }
    }

    LaunchedEffect (listOfUserSkills) {
        for (skillIterated in listOfUserSkills){
            val item: InterestAddedDataClass = InterestAddedDataClass(
                id = skillIterated.skill.id,
                selected = true,
                skill = skillIterated.skill.name
            )
            interestsViewModel.addSkill(item)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(separation),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (showAddPopUp){
            addInterestsPopUp(
                interestsViewModel.onDismissRequest,
                {interestsViewModel.onConfirmation(
                    userInfo!!.tokens.token,
                    userInfo!!.user.id
                ) },
                DIALOG_TITLE,
                listOfSkills,
                interestsViewModel.onSkillAddChange
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                ScreenTitleText(FIRST_TEXT)
                LazyColumn(
                    modifier = Modifier .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items(skills) { skillItem ->
                        interest(skillItem, interestsViewModel.onSkillChange)
                    }
                }

                if (listOfSkills.isNotEmpty()){
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
                                    interestsViewModel.onAdd()
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
                                    interestsViewModel.onDelete(
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
            }
        }

        if (showContinue){
            Row (
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CustomButton({interestsViewModel.onClick(
                    navController,
                    userInfo!!.user.id
                )},
                    if (loadingAddSkill) LOADING_ADD_INTEREST else BUTTON_TEXT
                )
            }
        }
    }
}

