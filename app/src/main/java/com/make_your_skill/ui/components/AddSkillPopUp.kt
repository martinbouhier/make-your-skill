package com.make_your_skill.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun addSkillPopUp(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String
) {
    AlertDialog(
        icon = {

        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = "")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}