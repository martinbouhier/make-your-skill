package com.make_your_skill.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.make_your_skill.R
import com.make_your_skill.dataClasses.Profile
import com.make_your_skill.dataClasses.users.UserDataClass
import com.make_your_skill.dataClasses.usersInterestedSkills.body.InterestAddedDataClass
import com.make_your_skill.helpers.functions.calculateRate
import com.make_your_skill.helpers.functions.capitalizeFirstLetter
import com.make_your_skill.ui.navigation.AppRoutes
import com.make_your_skill.ui.theme.BackgroundColor
import com.make_your_skill.ui.theme.cardInfo
import com.make_your_skill.ui.theme.cardRate
import com.make_your_skill.ui.theme.cardTitle
import java.util.Date

@Composable
fun ProfileCard(user: UserDataClass, skillSelected : InterestAddedDataClass, type : String, MATCH : String, navController: NavHostController) {
    val GAP = 8.dp
    val userSkill = user.user_skills!!.filter { item -> item.skill.id == skillSelected.id }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = GAP/2)
            .clickable { navController.navigate("${AppRoutes.PROFILE_SCREEN}/${user.id}?interestedSkillId=${skillSelected.id}&generateMatch=true") },
        colors = CardDefaults.cardColors(containerColor = BackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
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
                    text = capitalizeFirstLetter(user.firstname) + " " + capitalizeFirstLetter(user.lastname),
                    style = cardTitle
                )

                // info de la persona: costo x hora
                Text(
                    text = "$" + if (type != MATCH && userSkill.isNotEmpty())
                        userSkill[0].pricePerHour.toString() + "/hr" else "",
                    style = cardInfo,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            // Rating
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = calculateRate(user.votes,user.peopleVoted).toString(),
                    style = cardRate
                )
                Spacer(modifier = Modifier.size(GAP))
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star Icon",
                    tint = Color(0xE65451DC),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}