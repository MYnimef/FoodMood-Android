package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme


@Composable
fun MyLogInButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 10.dp)
            .width(290.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.onSecondaryContainer,
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.onSecondary,
        )
    ) {
        Text(text,  style = MaterialTheme.typography.titleMedium)
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