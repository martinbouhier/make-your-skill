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
import androidx.compose.runtime.LaunchedEffect
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
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.ui.components.CustomButton
import com.make_your_skill.ui.components.addSkillPopUp
import com.make_your_skill.ui.components.ScreenTitleText
import com.make_your_skill.ui.components.skill
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.theme.DarkPurple
import kotlinx.coroutines.delay


@Composable
fun SkillsScreen(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    cookieJar: InMemoryCookieJar
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

    val separation = 25.dp
    val BUTTON_TEXT = "CONTINUE 3/4"
    val FIRST_TEXT = "Skills"
    val DIALOG_TITLE = "Add skill"
    val PRICE_LABEL = "Price..."
    val LOADING_SKILLS = "Loading skills..."
    val LOADING_ADD_SKILLS = "Adding skills"

    LaunchedEffect(userInfo) {
        if (userInfo != null){
            val token = userInfo!!.tokens.token
            val sessionId = cookieJar.getSessionCookie().toString()
            skillsViewModel.getAllSkills(token,sessionId)
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
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ScreenTitleText(FIRST_TEXT)
                Column {
                    for (skillItem in skills) {
                        skill(skillItem, skillsViewModel.onSkillChange) // Llama al composable SkillItem para cada habilidad
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
        Row (
            modifier = Modifier
                .fillMaxSize()
        ) {
            CustomButton({skillsViewModel.onClick(
                navController,
                userInfo!!.tokens.token,
                cookieJar.getSessionCookie().toString(),
                userInfo!!.user.id
            )},
                if (loadingAddSkill) LOADING_ADD_SKILLS else BUTTON_TEXT
            )
        }
    }
}