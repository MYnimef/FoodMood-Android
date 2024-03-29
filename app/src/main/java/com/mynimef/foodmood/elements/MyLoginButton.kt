package com.mynimef.foodmood.elements

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
import com.mynimef.foodmood.theme.FoodMoodTheme

@Composable
fun MyLoginButton(
    text: String,
    enabledProvider: () -> Boolean,
    onClick: () -> Unit
) {
    val enabled = enabledProvider()

    MyLoginButton(
        text = text,
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun MyLoginButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .width(290.dp)
        ,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.onSecondary,
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        enabled = enabled,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun MyLoginButtonPreview() = FoodMoodTheme {
    MyLoginButton(
        text = "Sample",
        enabled = true,
        onClick = {}
    )
}