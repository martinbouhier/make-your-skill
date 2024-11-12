package com.make_your_skill.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.make_your_skill.R
import com.make_your_skill.dataClasses.users.UserDataClass
import com.make_your_skill.helpers.functions.capitalizeFirstLetter
import com.make_your_skill.ui.components.popUps.RatePopUp
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.screens.matchHistory.MatchHistoryViewModel
import com.make_your_skill.ui.theme.BackgroundColor
import com.make_your_skill.ui.theme.cardInfo
import com.make_your_skill.ui.theme.cardTitle


@Composable
fun ItemMatchHistory(
    navController: NavHostController,
    user: UserDataClass,
    token: String,
    matchedUserId: Int,
    viewModel: MatchHistoryViewModel,
    matchedSkill : String
) {
    val iconPainter: Painter = painterResource(id = R.drawable.user_profile_icon)
    val textPrice = 10
    val GAP = 8.dp

    if (viewModel.showRatePopUp.collectAsState().value) {
        RatePopUp(token, matchedUserId, viewModel)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = GAP / 2)
            .clickable { navController.navigate("${AppRoutes.PROFILE_SCREEN}/$matchedUserId??interestedSkillId=0&generateMatch=false") },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(GAP),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar de perfil
            Icon(
                painter = painterResource(R.drawable.user_profile_icon),
                contentDescription = "Avatar",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Column(modifier = Modifier.padding(start = 28.dp)) {
                // Nombre de la persona
                Text(
                    text = capitalizeFirstLetter(user.firstname) +
                            " " + capitalizeFirstLetter(user.lastname),
                    style = cardTitle
                )
                // Info de la persona: Skill matcheada
                Text(
                    text = "Interested Skill: " + matchedSkill,
                    style = cardInfo,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            // Rating
            Button(
                onClick = { viewModel.setRatePopUp(true) }, // MatchHistoryViewModel
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B61FF))
            ) {
                Text(text = "Rate", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemMatchHistoryPreview() {
    ItemMatchHistory(
        navController = rememberNavController(),
        user = UserDataClass(
            id = 1,
            firstname = "john",
            lastname = "doe",
            email = "john@gmail.com",
            phone = "1234 5678",
            isActive = true,
            votes = 0,
            peopleVoted = 0,
            dateOfBirth = "01/01/2000",
            createdAt = "01/01/2024",
            updatedAt = "01/01/2024"
        ),
        token = "token",
        matchedUserId = 0,
        viewModel = MatchHistoryViewModel(),
        matchedSkill = "Skill"
    )
}

