package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.data.models.enums.EClientNavigation
import com.mynimef.foodmood.presentation.screens.trainer.CreateViewModel

@Composable
fun CreateScreen(
    navigateTo: (route: EClientNavigation) -> Unit,
) {
    val viewModel: CreateViewModel = viewModel()

}