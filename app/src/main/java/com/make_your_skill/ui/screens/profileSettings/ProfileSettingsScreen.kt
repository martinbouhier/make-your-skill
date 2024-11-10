package com.make_your_skill.ui.screens.profileSettings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.R
import com.make_your_skill.ui.components.text.textFileds.ClickableText
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.theme.BackgroundColor2
import com.make_your_skill.ui.theme.styleTitle

@Composable
fun ProfileSettingsScreen(
    navController: NavHostController,
    singInViewModel: SingInViewModel
) {
    val profileSettingsViewModel: ProfileSettingsViewModel = viewModel()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val separation = screenHeight * 0.02f

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
        EditProfile(separation, navController,singInViewModel, profileSettingsViewModel)

    }
}

@Composable
fun EditProfile(
    separation: Dp,
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    profileSettingsViewModel: ProfileSettingsViewModel
){
    val userInfo by singInViewModel.signInInfo.collectAsState()

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
        Spacer(modifier = Modifier.height(separation*10))
        Text(
            text = "OTHERS",
            style = styleTitle,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(separation))
        ClickableText(text = "Add skills/interests", onClick = { navController.navigate(AppRoutes.ADD_SKILLS_INTEREST_SCREEN) }, color = BackgroundColor2)
        Spacer(modifier = Modifier.height(separation))
        ClickableText(text = "Match History", onClick = { navController.navigate(AppRoutes.MATCH_HISTORY_SCREEN) }, color = BackgroundColor2)
        Spacer(modifier = Modifier.height(separation))
        ClickableText(text = "Log Out", onClick = { singInViewModel.signOut(navController) }, color = BackgroundColor2)
        Spacer(modifier = Modifier.height(separation))
        ClickableText(text = "Delete Account", onClick = {
            profileSettingsViewModel.deleteUser(
                singInViewModel.getToken(),
                userInfo!!.user.id
            )
            singInViewModel.signOut(navController)}, color = BackgroundColor2)
    }
}