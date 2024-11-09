package com.make_your_skill.ui.screens.addSkillsAndInterests

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.ui.components.others.TwoOptionsScreen
import com.make_your_skill.ui.navigation.AppRoutes

@Composable
fun AddSkillsAndInterestsScreen(navController: NavHostController) {
    TwoOptionsScreen(navController = navController,
        firstButtonText = "INTERESTS",
        secondButtonText = "SKILLS",
        firstButtonAction = { navController.navigate(AppRoutes.INTERESTS_SCREEN + "?showContinue=false") },
        secondButtonAction = { navController.navigate(AppRoutes.SKILLS_SCREEN + "?showContinue=false") },
        description = "ADD SKILLS AND INTERESTS"
    )
}



@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val navController = rememberNavController()
    AddSkillsAndInterestsScreen(navController)
}

