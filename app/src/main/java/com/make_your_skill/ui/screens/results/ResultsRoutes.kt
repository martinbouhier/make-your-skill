package com.make_your_skill.ui.screens.results

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.make_your_skill.ui.screens.search.MatchSearchViewModel
import com.make_your_skill.ui.screens.singIn.SingInViewModel

@Composable
fun ResultsRoutes(
    navController: NavHostController,
    matchSearchViewModel: MatchSearchViewModel,
    singInViewModel: SingInViewModel,
    type: String
) {
    ResultsScreen(
        navController = navController,
        matchSearchViewModel = matchSearchViewModel,
        singInViewModel = singInViewModel,
        type = type
    )
}