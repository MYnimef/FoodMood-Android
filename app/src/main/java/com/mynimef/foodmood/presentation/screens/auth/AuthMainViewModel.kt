package com.mynimef.foodmood.presentation.screens.auth

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.flow.asSharedFlow

class AuthMainViewModel: ViewModel() {

    val navigation = Repository.authNavMain.asSharedFlow()
}