package com.make_your_skill.ui.screens.profile

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.R
import com.make_your_skill.dataClasses.usersInterestedSkills.body.GetUserInterestedSkillsById
import com.make_your_skill.dataClasses.usersSkills.body.GetUserSkillByUserId
import com.make_your_skill.helpers.cookies.InMemoryCookieJar
import com.make_your_skill.helpers.functions.calculateAge
import com.make_your_skill.helpers.functions.capitalizeFirstLetter
import com.make_your_skill.ui.components.text.CircularText
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.theme.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    userId: Int
) {
    val profileViewModel: ProfileViewModel = viewModel()
    val userInfo by singInViewModel.signInInfo.collectAsState()

    val userSearched by profileViewModel.userSearched.collectAsState()
    val loadingUserSearched by profileViewModel.loadingUserSearched.collectAsState()
    val errorUserSearched by profileViewModel.errorUserSearched.collectAsState()

    val listOfUserSkills by profileViewModel.listOfUserSkills.collectAsState()
    val loading by profileViewModel.loading.collectAsState()
    val error by profileViewModel.error.collectAsState()

    val listOfUserInterestedSkills by profileViewModel.listOfUserInterestedSkills.collectAsState()
    val loadingInterests by profileViewModel.loadingInterest.collectAsState()
    val errorInterests by profileViewModel.errorInterest.collectAsState()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val spacerSeparation =screenHeight * 0.04f

    LaunchedEffect(userInfo) {
        val token = userInfo!!.tokens.token

        //obtenemos la info del usuario del que estamos viendo el perfil
        profileViewModel.getUserById(token,userId)

        //hay que cambiar el id que esta tomando por el de userSearched (implementar endpoints)
        profileViewModel.getUserSkillByUserId(token,userId)

        //hay que cambiar el id que esta tomando por el de userSearched (implementar endpoints)
        profileViewModel.getUserInterestedSkillByUserId(token,userId)
    }
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(screenHeight * 0.01f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                if (loadingUserSearched || loading || loadingInterests){
                    Text(text = "Loading...")
                }
                else if (userSearched == null) {
                    Text(text = errorUserSearched.toString())
                }
                else {
                    Spacer(modifier = Modifier.height(screenHeight * 0.01f))
                    Image(
                        painter = painterResource(id = R.drawable.logo_purple),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .height(79.dp)
                            .width(78.dp),
                    )
                    Spacer(modifier = Modifier.height(spacerSeparation))
                    Text(
                        text = capitalizeFirstLetter(userSearched!!.firstname) +
                                " " +
                                capitalizeFirstLetter(userSearched!!.lastname),
                        style = styleTitle
                    )
                    Text(
                        text = userSearched!!.email,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(spacerSeparation))
                    Image(
                        painter = painterResource(id = R.drawable.user_profile_icon),
                        contentDescription = "User Profile Foto",
                        modifier = Modifier.size(100.dp),
                    )
                    Spacer(modifier = Modifier.height(spacerSeparation))
                    ContentProfile(
                        spacerSeparation,
                        calculateAge(userSearched!!.dateOfBirth),
                        listOfUserSkills,
                        loading,
                        error,
                        listOfUserInterestedSkills,
                        loadingInterests,
                        errorInterests
                    )
                }
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
fun ContentProfile(
    spacerSeparation : Dp,
    age: Int,
    listOfUserSkills: List<GetUserSkillByUserId>,
    loading: Boolean,
    error: String?,
    listOfUserInterestedSkills: List<GetUserInterestedSkillsById>,
    loadingInterests: Boolean,
    errorInterests: String?
) {
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
            if (loading){
                Text(text = "Loading skills...")
            }
            else if (error != null){
                Text(text = error)
            }
            else {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listOfUserSkills) { userSkill ->
                        CircularText(userSkill.skill.name)
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Interests",
                style = styleSubtitle,
                modifier = Modifier.padding(end = 8.dp)
            )
            if (loadingInterests){
                Text(text = "Loading interests...")
            }
            else if (errorInterests != null){
                Text(text = errorInterests)
            }
            else {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listOfUserInterestedSkills) { userSkill ->
                        CircularText(userSkill.skill.name)
                    }
                }
            }
        }
    }
}