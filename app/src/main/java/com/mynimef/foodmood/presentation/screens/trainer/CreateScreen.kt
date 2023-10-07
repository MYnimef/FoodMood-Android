package com.mynimef.foodmood.presentation.screens.trainer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun CreateScreen() {
    val viewModel: CreateViewModel = viewModel()
    Text("create")
}

@Preview(showBackground = true)
@Composable
private fun CreateScreenPreview() = FoodMoodTheme {
    CreateScreen()
}