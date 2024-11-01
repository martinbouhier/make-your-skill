package com.make_your_skill.ui.components

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
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import com.make_your_skill.ui.theme.DarkPurple

@Composable
fun CustomTextField(
    text: String,
    onTextChange: (String) -> Unit,
    label: String,
    onSubmit: () -> Unit,
    isSubmitEnabled: Boolean
) {
    Column(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = text,
            onValueChange = { newText -> onTextChange(newText) },
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
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done), // Establecer la acción del teclado
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
