package com.make_your_skill.ui.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.make_your_skill.dataClasses.usersInterestedSkills.body.InterestAddedDataClass
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.sliders.RangeSlider
import com.make_your_skill.ui.components.text.CircularText
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.theme.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MatchSearchScreen(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    matchSearchViewModel: MatchSearchViewModel,
    type: String
) {



    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = screenHeight * 0.02f
    val TITLE_TEXT = "Learn"
    val BUTTON_TEXT = if(type == "match") ("SEARCH POSIBLE MATCHES") else ("SEARCH PAID CLASSES")
    val LOADING = "Loading interests..."

    val listOfUserInterestedSkills by matchSearchViewModel.listOfUserInterestedSkills.collectAsState()
    val loadingInterests by matchSearchViewModel.loadingInterest.collectAsState()
    val errorInterests by matchSearchViewModel.errorInterest.collectAsState()
    val skillSelected by matchSearchViewModel.skillSelected.collectAsState()

    val userInfo by singInViewModel.signInInfo.collectAsState()

    LaunchedEffect(userInfo) {
        val token = userInfo!!.tokens.token
        matchSearchViewModel.getUserInterestedSkillByUserId(token,userInfo!!.user.id)
    }

    LaunchedEffect(Unit) {
        matchSearchViewModel.resetVariables()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(separation),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(separation * 2))
        Text(
            text = TITLE_TEXT,
            style = styleTitle,
            modifier = Modifier.padding(start = separation)
        )

        if (loadingInterests){
            Text(text = LOADING)
        }
        else if (errorInterests != null){
            Text(text = errorInterests.toString())
        }
        else if (listOfUserInterestedSkills.isNotEmpty()){
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .padding(8.dp)
            ) {
                LazyColumn {
                    item {
                        FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOfUserInterestedSkills.forEach { item ->
                                val finalItem = InterestAddedDataClass(item.skill.id,true,item.skill.name)
                                val selected: Boolean = skillSelected?.id == item.skill.id
                                CircularText(
                                    item.skill.name,
                                    {matchSearchViewModel.setSkillSelected(finalItem) },
                                    selected
                                )
                            }
                            
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(separation))

        Spacer(modifier = Modifier.height(separation))
        CustomButton(
            onClick = {
                if (skillSelected != null){
                    navController.navigate("${AppRoutes.RESULTS_SCREEN}?type=${type}")
                } },
            text = BUTTON_TEXT,
        )
        Spacer(modifier = Modifier.height(100.dp))
    }
}
