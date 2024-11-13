package com.make_your_skill.ui.components.sliders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.make_your_skill.ui.theme.*

@Composable
fun RangeSlider() {
    val minAgeLimit  = 18f
    val maxAgeLimit = 85f
    var sliderPosition by remember { mutableStateOf(minAgeLimit ..maxAgeLimit) }
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryContainerColor = MaterialTheme.colorScheme.secondaryContainer
    val paddingHorizontal = 40.dp
    val paddingVertical = 8.dp

    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Age Range",
            style = styleSubtitle
        )
        RangeSlider(
            value = sliderPosition,
            onValueChange = { range ->
                sliderPosition = range.start.toInt().toFloat()..
                        range.endInclusive.toInt().toFloat()
            },
            valueRange = minAgeLimit ..maxAgeLimit,
            colors = SliderDefaults.colors(
                thumbColor = primaryColor,
                activeTrackColor = primaryColor,
                inactiveTrackColor = secondaryContainerColor
            ),
            modifier = Modifier.padding(paddingHorizontal, paddingVertical),
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = paddingHorizontal/2),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "min. ${sliderPosition.start.toInt()}",
                style = styleSliderLimits
            )
            Text(
                text = "max. ${sliderPosition.endInclusive.toInt()}",
                style = styleSliderLimits
            )
        }
    }
}
