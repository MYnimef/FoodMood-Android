package com.mynimef.foodmood.presentation.screens.trainer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()
    Text("home")
}