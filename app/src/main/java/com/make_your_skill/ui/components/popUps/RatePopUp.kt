package com.make_your_skill.ui.components.popUps

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.make_your_skill.ui.components.icons.starts.getIconStart
import com.make_your_skill.ui.components.icons.starts.getIconStartSelected
import com.make_your_skill.ui.screens.matchHistory.MatchHistoryViewModel

@Composable
fun RatePopUp(
    token: String,
    matchedUserId: Int,
    viewModel: MatchHistoryViewModel
) {
    var selectedStars by remember { mutableStateOf(0) }

    AlertDialog(
        title = {
            Text(text = "Rate")
        },
        text = {
            Column (
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                StarsRow(selectedStars) { selected ->
                    selectedStars = selected
                }
            }
        },
        onDismissRequest = {
            viewModel.onDismissRequest()
        },
        confirmButton = {
            PopUpButton(
                onClick = { viewModel.onConfirmationRequest(
                    token,
                    matchedUserId,
                    selectedStars
                ) },
                text = "Add"
            )
        },
        dismissButton = {
            PopUpButton(
                onClick = { viewModel.onDismissRequest() },
                text = "Exit"
            )
        }
    )
}

@Composable
fun StarsRow(selectedStars: Int, onStarSelected: (Int) -> Unit) {
    Row(

    ) {
        for (i in 1..5) {
            StarButton(
                isSelected = i <= selectedStars,
                onClick = { onStarSelected(i) }
            )
            if (i < 5) Spacer(modifier = Modifier.width(24.dp))
        }
    }
}

@Composable
fun StarButton(isSelected: Boolean, onClick: () -> Unit) {
    Image(
        painter = if (isSelected) getIconStartSelected() else getIconStart(),
        contentDescription = "star",
        modifier = Modifier
            .size(24.dp)
            .clickable { onClick() }
    )
}

@Preview (showBackground = true)
@Composable
fun RatePopUpPreview() {
    RatePopUp(
        token = "token",
        matchedUserId = 1,
        viewModel = MatchHistoryViewModel()
    )
}
