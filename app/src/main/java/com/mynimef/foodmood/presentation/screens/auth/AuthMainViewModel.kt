package com.mynimef.foodmood.presentation.screens.auth

import androidx.lifecycle.ViewModel
import com.mynimef.data.RepositoryImpl
import kotlinx.coroutines.flow.asSharedFlow

class AuthMainViewModel: ViewModel() {

    val navigation = RepositoryImpl.authNavMain.asSharedFlow()
}