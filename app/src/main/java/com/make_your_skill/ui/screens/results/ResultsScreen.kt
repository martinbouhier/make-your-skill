package com.make_your_skill.ui.screens.results

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.make_your_skill.ui.theme.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import com.make_your_skill.ui.components.cards.ProfileCard
import com.make_your_skill.ui.screens.search.MatchSearchViewModel
import com.make_your_skill.ui.screens.singIn.SingInViewModel

@Composable
fun ResultsScreen(
    navController: NavHostController,
    type: String,
    singInViewModel: SingInViewModel,
    matchSearchViewModel: MatchSearchViewModel
) {
    val skillSelected by matchSearchViewModel.skillSelected.collectAsState()
    val usersSearched by matchSearchViewModel.usersSearched.collectAsState()
    val loadingUsersSearch by matchSearchViewModel.loadingUsersSearch.collectAsState()
    val errorUsersSearched by matchSearchViewModel.errorUsersSearched.collectAsState()
    val listOfUserSkills by matchSearchViewModel.listOfUserSkills.collectAsState()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = 10.dp
    val MATCH = "match"
    val PAID = "paid"

    LaunchedEffect(Unit) {
        val token = singInViewModel.getToken()
        val userId = singInViewModel!!.signInInfo.value!!.user.id

        if (type == PAID){
            matchSearchViewModel.findManyBySkillsAndInterests(
                token,
                skillSelected!!.id.toString()
            )
        }
        else if (type == MATCH){
            matchSearchViewModel.getUserSkillByUserId(token,userId)
        }
    }

    LaunchedEffect(listOfUserSkills) {
        val token = singInViewModel.getToken()
        val mySkillsIds: List<Int> = listOfUserSkills.map { item -> item.skill.id }
        val mySkillsIdsString: String = if (mySkillsIds.isNotEmpty()) mySkillsIds.joinToString(separator = ",") else ""

        if (mySkillsIds.isNotEmpty()){
            matchSearchViewModel.findManyBySkillsAndInterests(
                token,
                skillSelected!!.id.toString(),
                mySkillsIdsString
            )
        }
        else {
            matchSearchViewModel.findManyBySkillsAndInterests(
                token,
                skillSelected!!.id.toString()
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(separation * 2),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.1f))
        Text(
            text = if (type == MATCH) "Matches" else "Paid Clases",
            style = styleSubtitle,
            modifier = Modifier
                .padding(separation)
        )
        if (usersSearched.isNotEmpty()){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(separation) // Espacio entre cards
            ) {
                items(usersSearched) { user ->
                    ProfileCard(user = user, skillSelected!!, type, MATCH, navController = navController)
                    HorizontalDivider(modifier = Modifier.background(color = Color(0x784E40EA)))
                }
            }
        }
        else if (loadingUsersSearch){
            Text(text = "Loading...")
        }
        else if (errorUsersSearched != null){
            Text(text = errorUsersSearched.toString())
        }
    }
}
