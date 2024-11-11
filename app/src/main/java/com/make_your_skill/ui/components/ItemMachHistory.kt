package com.make_your_skill.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.make_your_skill.R
import com.make_your_skill.dataClasses.users.UserDataClass
import com.make_your_skill.ui.components.popUps.RatePopUp
import com.make_your_skill.ui.screens.matchHistory.MatchHistoryViewModel


@Composable
fun ItemMatchHistory(
    user: UserDataClass,
    token: String,
    matchedUserId: Int,
    viewModel: MatchHistoryViewModel
) {
    val iconPainter: Painter = painterResource(id = R.drawable.user_profile_icon)
    val textPrice = 10

    if (viewModel.showRatePopUp.collectAsState().value) {
        RatePopUp( token, matchedUserId, viewModel )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = iconPainter,
            contentDescription = "Icono de usuario",
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = user.firstname,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7B61FF)
            )
            Text(
                text = ("$ $textPrice/h"),
                fontSize = 15.sp,
                color = Color(0xFFB0A4FF)
            )
        }
        Button(
            onClick = { viewModel.setRatePopUp(true) }, // MatchHistoryViewModel
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B61FF))
        ) {
            Text(text = "Rate", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemMatchHistoryPreview() {
    ItemMatchHistory(
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
        viewModel = MatchHistoryViewModel()
    )
}

