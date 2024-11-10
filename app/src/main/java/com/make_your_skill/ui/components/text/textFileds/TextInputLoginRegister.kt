package com.make_your_skill.ui.components.text.textFileds

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.make_your_skill.ui.components.icons.eyes.getIconEyeClosed
import com.make_your_skill.ui.components.icons.eyes.getIconEyeOpen


@Composable
fun TextInputLogin(
    label: String,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier,
    text: String,
    onChange: (String) -> Unit,
    error: String?,
    focusRequester: FocusRequester,
    onImeAction: () -> Unit = {},
    nextFocusRequester: FocusRequester? = null,
    color: Color = Color.White
) {
    var showPassword by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current


    OutlinedTextField(
        value = text,
        onValueChange = { newText -> onChange(newText) },
        label = { Text(text = label, fontSize = 16.sp, color = color) },
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        painter = if (showPassword) getIconEyeOpen() else getIconEyeClosed(),
                        contentDescription = if (showPassword) "Ocultar contraseña" else "Mostrar contraseña",
                        tint = color
                    )
                }
            }
            if (error != null) {
                Text(
                    text = "!",
                    color = Color.Red,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            capitalization = if (!isPassword) KeyboardCapitalization.Words else KeyboardCapitalization.None,
            autoCorrectEnabled = !isPassword,
            imeAction = if (nextFocusRequester != null) ImeAction.Next else ImeAction.Done,
            keyboardType = if (!showPassword && isPassword) KeyboardType.Password else KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                if (text.isNotEmpty()) {
                    nextFocusRequester?.requestFocus()
                }
            },
            onDone = {
                if (text.isNotEmpty()) {
                    keyboardController?.hide()
                    onImeAction()
                }
            }
        ),
        shape = RoundedCornerShape(50.dp),
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        visualTransformation = if (isPassword && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = if (error != null) Color.Red else color,
            unfocusedIndicatorColor = if (error != null) Color.Red else color,
            focusedTextColor = color,
            unfocusedTextColor = color,
            cursorColor = color
        )
    )
}