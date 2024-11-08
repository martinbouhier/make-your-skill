package com.make_your_skill.ui.components.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.make_your_skill.R

@Composable
fun BackButton(navController: NavHostController, color: Color) {
        CustomIconButton(iconResId = R.drawable.back, { navController.popBackStack()}, color)
}