package com.make_your_skill.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.make_your_skill.R
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.theme.*

@Composable
fun ProfileScreen(navController: NavHostController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val spacerSeparation = 32.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(screenHeight * 0.1f))
            Image(
                painter = painterResource(id = R.drawable.logo_purple),
                contentDescription = "App Logo",
                modifier = Modifier.height(79.dp).width(78.dp),
            )
            Spacer(modifier = Modifier.height(spacerSeparation))
            Text(
                text = "Persona Random",
                style = styleTitle
            )
            Spacer(modifier = Modifier.height(spacerSeparation))
            Image(
                painter = painterResource(id = R.drawable.user_profile_icon),
                contentDescription = "User Profile Foto",
                modifier = Modifier
                    .height(79.dp)
                    .width(78.dp),
            )
            Spacer(modifier = Modifier.height(spacerSeparation))

           Row{
               Text(
                   text = "Age: ", // Cambia esto por el número real
                   style = styleSubtitle,
               )
               Text(
                   text = "25", // Cambia esto por el número real
                   style = styleSubtitle,
                   fontSize = 30.sp,

               )
           }


            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

            Spacer(modifier = Modifier.height(spacerSeparation))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Skills",
                    style = styleSubtitle,

                )
                SkillsChips(skills = listOf("Kotlin", "Compose", "Jetpack"))
            }

        }


        IconButton(
            onClick = { navController.navigate(AppRoutes.SETTINGS_SCREEN) },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {

            val iconPainter: Painter = painterResource(id = R.drawable.settings_icon)


            Image(
                painter = iconPainter,
                contentDescription = null,
                modifier = Modifier.size(300.dp),
            )
        }
    }
}

@Composable
fun SkillsChips(skills: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        skills.forEach { skill ->

            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .background(Color.LightGray, shape = CircleShape)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(text = skill, color = Color.White)
            }
        }
    }
}