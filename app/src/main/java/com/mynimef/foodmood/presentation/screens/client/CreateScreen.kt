package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.presentation.screens.trainer.CreateViewModel

@Composable
fun CreateScreen() {
    val viewModel: CreateViewModel = viewModel()
    Text("create")
}