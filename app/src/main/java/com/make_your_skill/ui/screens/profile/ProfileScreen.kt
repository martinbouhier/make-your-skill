package com.make_your_skill.ui.screens.profile

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.R
import com.make_your_skill.dataClasses.auth.dto.SignInDto
import com.make_your_skill.dataClasses.skills.skillDataClass
import com.make_your_skill.dataClasses.users.UserDataClass
import com.make_your_skill.dataClasses.usersInterestedSkills.body.GetUserInterestedSkillsById
import com.make_your_skill.dataClasses.usersSkills.body.GetUserSkillByUserId
import com.make_your_skill.helpers.functions.calculateAge
import com.make_your_skill.helpers.functions.calculateRate
import com.make_your_skill.helpers.functions.capitalizeFirstLetter
import com.make_your_skill.ui.components.buttons.CustomButton
import com.make_your_skill.ui.components.icons.starts.getIconStart
import com.make_your_skill.ui.components.icons.starts.getIconStartSelected
import com.make_your_skill.ui.components.text.CircularText
import com.make_your_skill.ui.screens.singIn.SingInViewModel
import com.make_your_skill.ui.theme.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    singInViewModel: SingInViewModel,
    userId: Int,
    interestedSkillId: Int?,
    generateMatch: Boolean
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
    val SCREEN_HEIGHT_GAP = screenHeight * 0.04f
    val GAP = 16.dp

    LaunchedEffect(userInfo) {
        val token = userInfo!!.tokens.token

        //obtenemos la info del usuario del que estamos viendo el perfil
        profileViewModel.getUserById(token,userId)

        //hay que cambiar el id que esta tomando por el de userSearched (implementar endpoints)
        profileViewModel.getUserSkillByUserId(token,userId)

        //hay que cambiar el id que esta tomando por el de userSearched (implementar endpoints)
        profileViewModel.getUserInterestedSkillByUserId(token,userId)

    }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(GAP),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (loadingUserSearched || loading || loadingInterests){
                Text(text = "Loading...")
            }
            else if (userSearched == null) {
                Text(text = errorUserSearched.toString())
            }
            else {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_purple),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .height(79.dp)
                            .width(78.dp),
                    )
                    Spacer(modifier = Modifier.height(GAP))

                    // NOMBRE DEL USUARIO
                    Text(
                        text = capitalizeFirstLetter(userSearched!!.firstname) +
                                " " +
                                capitalizeFirstLetter(userSearched!!.lastname),
                        style = styleTitle
                    )

                    // MAIL DEL USUARIO
                    Text(
                        text = userSearched!!.email,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(GAP))

                    // ICONO DE PERFIL
                    Image(
                        painter = painterResource(id = R.drawable.user_profile_icon),
                        contentDescription = "User Profile Foto",
                        modifier = Modifier.size(80.dp),
                    )
                    Spacer(modifier = Modifier.height(GAP))

                    // FILA DE ESTRELLAS
                    Column (
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        var selectedStars = if (userSearched != null)
                            calculateRate(userSearched!!.votes,userSearched!!.peopleVoted).toInt()
                        else 0
                        StarsRow(selectedStars) { selected ->
                            selectedStars = selected
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    // CONTENIDO DEL PERFIL
                    ContentProfile(
                        SCREEN_HEIGHT_GAP,
                        calculateAge(userSearched!!.dateOfBirth),
                        listOfUserSkills,
                        loading,
                        error,
                        listOfUserInterestedSkills,
                        loadingInterests,
                        errorInterests
                    )
                }

                Column (
                    modifier = Modifier.padding(bottom = 25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    // BOTON DE CONTACTO - EN CASO DE ESTAR HABILITADO
                    if (userInfo!!.user.id != userId){
                        ContactButton(
                            userSearched!!,
                            singInViewModel,
                            profileViewModel,
                            userInfo!!,
                            interestedSkillId!!,
                            generateMatch
                        )
                    }
                }
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
    val horizontalPadding = 35.dp
    val verticalPadding = 8.dp

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = verticalPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Age: ",
                style = styleSubtitle,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = age.toString(),
                style = styleTitle,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = Purple,
            modifier = Modifier.padding(horizontal = horizontalPadding - verticalPadding)
        )
        Spacer(modifier = Modifier.height(spacerSeparation))
   
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = horizontalPadding)
                .padding(vertical = verticalPadding),
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
                .padding(start = horizontalPadding)
                .padding(vertical = verticalPadding * 2),
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
@Composable
fun StarsRow(selectedStars: Int, onStarSelected: (Int) -> Unit) {
    Row(

    ) {
        for (i in 1..5) {
            StarButton(isSelected = i <= selectedStars)
            if (i < 5) Spacer(modifier = Modifier.width(24.dp))
        }
    }
}
@Composable
fun StarButton(isSelected: Boolean) {
    Image(
        painter = if (isSelected) getIconStartSelected() else getIconStart(),
        contentDescription = "star",
        modifier = Modifier.size(24.dp)
    )
}

@Composable
fun ContactButton(userSearched: UserDataClass,
                  singInViewModel: SingInViewModel,
                  profileViewModel: ProfileViewModel,
                  userInfo: SignInDto,
                  interestedSkillId: Int,
                  generateMatch: Boolean
) {
    val context = LocalContext.current

    val phoneNumber = if (userSearched.phone.startsWith("549")) {
        userSearched.phone
    } else {
        "549${userSearched.phone}"
    }

    val whatsappApiUrl = "https://wa.me/${phoneNumber}"

    CustomButton(onClick = {
        if (generateMatch){
            profileViewModel.createMatch(
                token = singInViewModel.getToken(),
                userAid = userInfo.user.id,
                userBid = userSearched.id,
                skillBid = interestedSkillId
            )
        }
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(whatsappApiUrl)
        }
        context.startActivity(intent)

    }, text = "MATCH")
}