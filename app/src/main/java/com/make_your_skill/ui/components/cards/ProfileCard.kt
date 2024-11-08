package com.make_your_skill.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import com.make_your_skill.R
import com.make_your_skill.dataClasses.Profile
import com.make_your_skill.ui.theme.cardInfo
import com.make_your_skill.ui.theme.cardRate
import com.make_your_skill.ui.theme.cardTitle

@Composable
fun ProfileCard(profile: Profile) {
    val separation = 10.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = separation/2)
            .clickable { /* Aca se incorporará algun dia la lógica para redirigir a WPP */},
        elevation = CardDefaults.cardElevation(separation/2)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(separation),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar de perfil
            Icon(
                painter = painterResource(R.drawable.user_profile_icon), // Reemplaza con tu ícono de avatar
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(separation * 4)
                    .padding(end = separation),
                tint = MaterialTheme.colorScheme.primary
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Nombre de la persona
                Text(
                    text = profile.name,
                    style = cardTitle
                )
                // info de la persona: costo x hora
                Text(
                    text = profile.info,
                    style = cardInfo
                )
            }

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = profile.rate,
                    style = cardRate
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star Icon",
                    tint = Color(0xE65451DC),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}