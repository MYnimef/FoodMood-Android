package com.mynimef.foodmood.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = viewModel()
    Text("settings")
}