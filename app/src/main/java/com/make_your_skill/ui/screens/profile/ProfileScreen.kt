package com.make_your_skill.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.make_your_skill.R
import com.make_your_skill.helpers.functions.calculateAge
import com.make_your_skill.helpers.functions.capitalizeFirstLetter
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.theme.*

@Composable
fun ProfileScreen(
    navController: NavHostController,
    singInViewModel: SingInViewModel
) {
    val userInfo by singInViewModel.signInInfo.collectAsState()
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
                modifier = Modifier
                    .height(79.dp)
                    .width(78.dp),
            )
            Spacer(modifier = Modifier.height(spacerSeparation))
            Text(
                text = capitalizeFirstLetter(userInfo!!.user.firstname) +
                        " " +
                        capitalizeFirstLetter(userInfo!!.user.lastname),
                style = styleTitle
            )
            Spacer(modifier = Modifier.height(spacerSeparation))
            Image(
                painter = painterResource(id = R.drawable.user_profile_icon),
                contentDescription = "User Profile Foto",
                modifier = Modifier
                    .height(142.dp)
                    .width(142.dp),
            )
            Spacer(modifier = Modifier.height(spacerSeparation))
            ContentProfile(spacerSeparation, calculateAge(userInfo!!.user.dateOfBirth))


        }


        IconButton(
            onClick = { navController.navigate(AppRoutes.SETTINGS_SCREEN) },
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {

            val iconPainter: Painter = painterResource(id = R.drawable.settings_icon)

                Image(
                    painter = iconPainter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .scale(2f),
                )


        }
    }
}
@Composable
fun ContentProfile(spacerSeparation : Dp,age: Int) {
    Column(
        modifier = Modifier.padding(16.dp) 
    ) {
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Age: ",
                style = styleSubtitle,
                modifier = Modifier.weight(1f) 
            )
            Text(
                text = age.toString(),
                style = styleSubtitle,
                fontSize = 30.sp,
                modifier = Modifier.weight(1f), 
                textAlign = TextAlign.Center 
            )
        }
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(spacerSeparation))
   
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Skills",
                style = styleSubtitle,
                modifier = Modifier.padding(end = 8.dp)
            )

           
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listOf("Kotlin", "Compose", "Jetpack", "Java", "Android")) { skill ->
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
    }
}