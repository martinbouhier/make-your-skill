package com.make_your_skill.ui.components.bars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.make_your_skill.ui.components.icons.navigation.getIconBack
import com.make_your_skill.ui.components.icons.navigation.getIconSettings
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.theme.BackgroundColor2
import com.make_your_skill.ui.theme.White

@Composable
fun CustomTopBar(navController: NavController, currentRoute: String, userId: Int?, loggedUserId: Int?) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if(currentRoute == AppRoutes.LOGIN_SCREEN || currentRoute == AppRoutes.REGISTER_SCREEN) BackgroundColor2 else Color.Transparent),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                painter = getIconBack(),
                contentDescription = "Back",
                tint = if(currentRoute == AppRoutes.LOGIN_SCREEN || currentRoute == AppRoutes.REGISTER_SCREEN) White else Color(0xFF828693)
            )
        }



        if (currentRoute.startsWith(AppRoutes.PROFILE_SCREEN) && userId == loggedUserId && userId != null) {
            IconButton(onClick = { navController.navigate(AppRoutes.SETTINGS_SCREEN) }) {
                    Image(
                        painter = getIconSettings(),
                        contentDescription = "Settings",
                    )
            }
        }else{
            Box{}
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun PreviewCustomTopBar() {
    CustomTopBar(navController = NavController(LocalContext.current), currentRoute = AppRoutes.PROFILE_SCREEN)
}*/