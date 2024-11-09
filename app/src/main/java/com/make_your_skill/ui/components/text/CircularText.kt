package com.make_your_skill.ui.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircularText(
    text: String,
    onClick: () -> Unit = {},
    selected: Boolean = false
) {
    Box(
        modifier = Modifier
            .background(
                color = if (selected) Color(0xFF4E40EA).copy(alpha = 0.8f) else Color.White, // Cambiar color según `selected`,
                shape = CircleShape
            )
            .border(
                width = 2.dp,
                color = if (!selected) Color(0xFF4E40EA).copy(alpha = 0.8f) else Color.White,
                shape = CircleShape
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .wrapContentSize()
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            color = if (!selected) Color(0xFF4E40EA).copy(alpha = 0.8f) else Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircularTextPreview() {
    CircularText(text = "Texto largo para probar si la burbuja se adapta")
}