package com.make_your_skill.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.make_your_skill.ui.theme.DarkPurple

@Composable
fun CustomTextField(
    text: String,
    onTextChange: (String) -> Unit,
    label: String
) {
    Column(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = text,
            onValueChange = { newText -> onTextChange(newText) },
            label = { Text(text = label) },
            textStyle = TextStyle(color = DarkPurple, fontSize = 16.sp), // Color de texto violeta
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White) // Fondo blanco del TextField
        )
        // Underline
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp) // Grosor de la línea
                .background(DarkPurple) // Color de la línea
        )
    }
}
