package com.make_your_skill.ui.components.text.textFileds

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextInputLogin(
    label: String,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier,
    text: String,
    onChange: (String) -> Unit,
    error: String?
) {
    OutlinedTextField(
        value = text,
        onValueChange = { newText -> onChange(newText) },
        label = { Text(text = label, fontSize = 16.sp, color = Color.White) },
        trailingIcon = {
            if (error != null) {
                Text(
                    text = "!",
                    color = Color.Red,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        },
        shape = RoundedCornerShape(50.dp),
        modifier = modifier
            .fillMaxWidth(),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = if (error != null) Color.Red else Color.White,
            unfocusedIndicatorColor = if (error != null) Color.Red else Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White

        )
    )
}