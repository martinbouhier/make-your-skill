package com.make_your_skill.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun addSkillPopUp(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    onPriceAddChange: (String) -> Unit,
    label: String,
    text: String
) {
    val TITLE = "Title..."
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
          Column {
              CustomTextField(text,onPriceAddChange,label)
          }
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