package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.EClientNavigation
import com.mynimef.foodmood.data.models.enums.EFoodType
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.elements.MyPolygonLayout
import com.mynimef.foodmood.presentation.screens.shared.SignUpViewModel
import com.mynimef.foodmood.presentation.screens.client.CreateViewModel

@Composable
fun CreateScreen(
    viewModel: CreateViewModel,
    navigateTo: (route: String) -> Unit, ) {

}