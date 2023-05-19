package com.mynimef.foodmood.presentation.elements

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme


@Composable
fun MyLogInButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.onSecondaryContainer,
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.onSecondary,
        )
    ) {
        Text(text)
    }
}


@Preview
@Composable
private fun PrevMyLoginButton() {
    FoodMoodTheme {
        MyLogInButton(text = "n") {

        }
    }
}