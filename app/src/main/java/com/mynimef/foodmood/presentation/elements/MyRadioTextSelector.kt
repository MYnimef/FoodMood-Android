package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MyRadioTextSelector(
    text: String,
    selectedProvider: () -> Boolean,
    onClick: () -> Unit
) {
    val selected = selectedProvider()

    TextButton(
        modifier = Modifier
            .fillMaxWidth()
        ,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                ,
                selected = selected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
            )
            Text(
                text = text,
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.titleMedium,
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyRadioTextSelector() {
    FoodMoodTheme {
        val selectedState = remember { mutableStateOf(false) }

        MyRadioTextSelector(
            text = "sample",
            selectedProvider = { selectedState.value },
            onClick = {},
        )
    }
}