package com.make_your_skill.ui.components.popUps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ActionConfirmationDialogPopUp(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    confirmButtonAction1: () -> Unit,
    confirmButtonAction2: () -> Unit,
    text: String,
    title: String
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = title,
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    textAlign = TextAlign.Center
                )
            }
        },
        confirmButton = {
            PopUpButton(
                text = "Confirm",
                onClick = confirmButtonAction1
            )
        },
        dismissButton = {
            PopUpButton(
                text = "Cancel",
                onClick = confirmButtonAction2
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DeletePopUpPreview() {
    ActionConfirmationDialogPopUp( onDismissRequest = {}, onConfirmation = {}, confirmButtonAction1 = {}, confirmButtonAction2 = {}, text = "Are you sure you want to permanently delete your account? This action cannot be undone.", "titulo")
}
