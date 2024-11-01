package com.make_your_skill.ui.screens.profileSettings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.R
import com.make_your_skill.ui.components.ClickableText
import com.make_your_skill.ui.theme.BackgroundColor2
import com.make_your_skill.ui.theme.styleTitle

@Composable
fun ProfileSettingsScreen(navController: NavHostController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = 16.dp


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(separation),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(screenHeight * 0.01f))
        Image(
            painter = painterResource(id = R.drawable.logo_purple),
            contentDescription = "App Logo",
            modifier = Modifier
                .width(78.dp)
                .height(79.dp)
        )
        Spacer(modifier = Modifier.height(separation))
        Text(
            text = "CONFIGURATION",
            style = styleTitle,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(separation*2))
        EditProfile(separation)

    }
}

@Composable
fun EditProfile(separation: Dp){
    Column (
        modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top){
        Text(
            text = "Edit Profile",
            style = styleTitle,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(separation))

        ClickableText(text = "Reset password", onClick = { /*TODO*/ }, color = BackgroundColor2)
        Spacer(modifier = Modifier.height(separation*15))
        Text(
            text = "OTHERS",
            style = styleTitle,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(separation))
        ClickableText(text = "Add skills/interests", onClick = { /*TODO*/ }, color = BackgroundColor2)
        Spacer(modifier = Modifier.height(separation))
        ClickableText(text = "Match History", onClick = { /*TODO*/ }, color = BackgroundColor2)
        Spacer(modifier = Modifier.height(separation))
        ClickableText(text = "Log Out", onClick = { /*TODO*/ }, color = BackgroundColor2)
        Spacer(modifier = Modifier.height(separation))
        ClickableText(text = "Delete Account", onClick = { /*TODO*/ }, color = BackgroundColor2)

    }
}


@Preview(showBackground = true)
@Composable
fun ProfileSettingsScreenPreview() {
    val navController = rememberNavController()
    ProfileSettingsScreen(navController)
}