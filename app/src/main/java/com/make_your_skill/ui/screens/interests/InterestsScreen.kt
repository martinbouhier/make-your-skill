package com.make_your_skill.ui.screens.interests

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.make_your_skill.dataClasses.usersInterestedSkills.body.InterestAddedDataClass
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.interest
import com.make_your_skill.ui.components.popUps.addInterestsPopUp
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

    val GAP = 16.dp
    val BUTTON_TEXT = "CONTINUE"
    val FIRST_TEXT = "Interests"
    val DIALOG_TITLE = "Add interests"
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
            .padding(GAP),
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                ScreenTitleText(FIRST_TEXT)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(top = GAP * 3)
                            .padding(horizontal = GAP),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        /*val sampleSkills = listOf(
                        InterestAddedDataClass(1, true, "Kotlin"),
                        InterestAddedDataClass(2, true, "Java"),
                        InterestAddedDataClass(3, true, "Python"),
                        InterestAddedDataClass(4, true, "C++"),
                    )*/
                        items(skills) { skillItem ->
                            interest(skillItem, interestsViewModel.onSkillChange)
                        }
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
                            .clickable { interestsViewModel.onAdd() },
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = DarkPurple
                    )

                    Text(
                        text = "Delete",
                        modifier = Modifier
                            .padding(16.dp)
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
            Spacer(modifier = Modifier.height(75.dp))
        }

        Column(
            modifier = Modifier.padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            if (showContinue){
                CustomButton(
                    {
                        interestsViewModel.onClick(
                            navController,
                            userInfo!!.user.id
                        )
                    },
                    if (loadingAddSkill) LOADING_ADD_INTEREST else BUTTON_TEXT
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InterestedSkillsScreenPreview() {
    InterestedSkillsScreen(
        navController = rememberNavController(),
        singInViewModel = SingInViewModel(),
        showContinue = true
    )
}

