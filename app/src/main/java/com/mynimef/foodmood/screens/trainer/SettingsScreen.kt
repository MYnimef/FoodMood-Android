package com.mynimef.foodmood.screens.trainer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.theme.FoodMoodTheme

@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = viewModel()
    Text("settings")
}

@Preview
@Composable
private fun SettingsScreenPreview() = FoodMoodTheme {
    SettingsScreen()
}