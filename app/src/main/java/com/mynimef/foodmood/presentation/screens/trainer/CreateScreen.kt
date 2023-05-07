package com.mynimef.foodmood.presentation.screens.trainer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CreateScreen() {
    val viewModel: CreateViewModel = viewModel()
    Text("create")
}