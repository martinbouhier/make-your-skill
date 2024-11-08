package com.make_your_skill.ui.components

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.make_your_skill.R
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.theme.Purple

@Composable
fun BottomAppBarContent(navController: NavHostController) {
    Row (
      modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomIconButton(iconResId = R.drawable.logo_purple, {navController.navigate(AppRoutes.INTERESTED_SKILLS_SCREEN)}, Purple)
        CustomIconButton(iconResId = R.drawable.search, {navController.navigate(AppRoutes.MAIN_SCREEN)},Purple)
        CustomIconButton(iconResId = R.drawable.profile, {navController.navigate(AppRoutes.PROFILE_SCREEN)},Purple)

    }


}