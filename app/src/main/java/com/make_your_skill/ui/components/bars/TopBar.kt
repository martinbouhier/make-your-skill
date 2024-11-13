package com.make_your_skill.ui.components.bars

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.make_your_skill.ui.components.icons.navigation.getIconBack
import com.make_your_skill.ui.components.icons.navigation.getIconSettings
import com.make_your_skill.ui.navigation.AppRoutes

@Composable
fun CustomTopBar(navController: NavController, currentRoute: String, userId: Int?, loggedUserId: Int?) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = { navController.popBackStack() }) {
            Image(
                painter = getIconBack(),
                contentDescription = "Back",
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