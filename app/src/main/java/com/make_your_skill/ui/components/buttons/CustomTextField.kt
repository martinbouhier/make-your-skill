package com.make_your_skill.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.sp
import com.make_your_skill.ui.theme.DarkPurple

@Composable
fun CustomTextField(
    text: String,
    onTextChange: (String) -> Unit,
    label: String,
    onSubmit: () -> Unit,
    isSubmitEnabled: Boolean,
    isNumericOnly: Boolean = false,
    maxLength: Int = 10
) {
    var textFieldValue by remember { mutableStateOf(if (text.isEmpty()) "" else text) }
    var isPlaceholderVisible by remember { mutableStateOf(text.isEmpty()) }
    Column(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = text,
            onValueChange = { newText ->

                if (isNumericOnly) {

                    val isValidNumber = newText.all { it.isDigit() || it == '.' }

                    if (isValidNumber && newText.length <= maxLength) {
                        if (newText == "") {
                            isPlaceholderVisible = true
                        } else {
                            isPlaceholderVisible = false
                        }
                        textFieldValue = newText
                        onTextChange(newText)
                    }
                } else {

                    if (newText.length <= maxLength) {
                        textFieldValue = newText
                        onTextChange(newText)
                    }
                }
            },
            label = { Text(text = label) },
            textStyle = TextStyle(color = DarkPurple, fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .onKeyEvent { event ->
                    if (isSubmitEnabled && event.key == Key.Enter && event.type == KeyEventType.KeyUp) {
                        onSubmit() // Llama a onSubmit
                        true // Indica que el evento fue manejado
                    } else {
                        false
                    }
                },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Words
            ), // Establecer la acción del teclado
            keyboardActions = KeyboardActions(
                onDone = {
                    if (text.isNotBlank()) {
                        onSubmit() // Ejecutar el submit al presionar "Done" en el teclado
                    }
                }
            )
        )
        // Underline
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(DarkPurple)
        )
    }
}
