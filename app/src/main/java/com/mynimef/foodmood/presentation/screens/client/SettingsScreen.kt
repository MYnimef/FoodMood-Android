package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.presentation.screens.trainer.SettingsViewModel

@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = viewModel()
    Text("settings")
}