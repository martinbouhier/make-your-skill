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
import androidx.compose.material3.Icon

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.make_your_skill.R
import com.make_your_skill.model.UserModel


@Composable
fun ItemMatchHistory(persona: UserModel) {
    val iconPainter: Painter = painterResource(id = R.drawable.user_profile_icon)
    val textPrice = persona.price
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
                text = persona.name,
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
            onClick = { /* Acción para el botón "Calificar" */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B61FF))
        ) {
            Text(text = "Calificar", color = Color.White)
        }
    }
}

