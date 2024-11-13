package com.make_your_skill.ui.screens.matchHistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.dataClasses.users.UserDataClass
import com.make_your_skill.ui.components.ItemMatchHistory
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.theme.BackgroundColor2
import com.make_your_skill.ui.theme.DarkBlue
import com.make_your_skill.ui.theme.Purple

@Composable
fun MatchHistoryScreen(
    navController: NavHostController, // TODO: Borrar?
    singInViewModel: SingInViewModel
) {
    val viewModel : MatchHistoryViewModel = viewModel()
    val loading by viewModel.loading.collectAsState() //TODO: Que hace esto?
    val error by viewModel.error.collectAsState() //TODO: Que hace esto?
    val userInfo by singInViewModel.signInInfo.collectAsState()
    val listOfUserMatches by viewModel.listOfUserMatches.collectAsState()

    LaunchedEffect(userInfo) {
        val token = userInfo!!.tokens.token

        // Obtenemos
        viewModel.findMatchesByUserId(token, userInfo!!.user.id)
    }

    val GAP = 16.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(GAP)
    ) {
        Spacer(modifier = Modifier.height(GAP))
        Text(
            text = "Match history",
            fontSize = 20.sp,
            color = BackgroundColor2,
            modifier = Modifier.padding(bottom = GAP)
        )
        LazyColumn {
            if (loading) {
                item { Text(text = "Loading...") }
            } else if (error != null) {
                item { Text(text = error!!) }
            } else if (listOfUserMatches == null) {
                item { Text(text = "No matches found") }
            } else {
                    items(listOfUserMatches!!) { item ->
                        var matchedUser : UserDataClass = item.userB
                        var matchedSkill : String = item.skillB.name

                        if( item.userB.id == userInfo!!.user.id){
                            matchedUser = item.userA
                            matchedSkill = item.skillA.name
                        }

                        ItemMatchHistory(
                            navController = navController,
                            user = matchedUser,
                            token = userInfo!!.tokens.token,
                            matchedUserId = matchedUser.id,
                            viewModel = viewModel,
                            matchedSkill = matchedSkill
                        )
                        HorizontalDivider(
                            color = Color(0x784E40EA),
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 1.dp)
                        )
                    }
                }
            }
    }
}

@Preview(showBackground = true)
@Composable
fun MatchHistoryScreenPreview() {
   val navController = rememberNavController()
    val singInViewModel = SingInViewModel()
    MatchHistoryScreen(navController, singInViewModel)
}