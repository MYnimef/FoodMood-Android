package com.mynimef.foodmood.screens.auth

import androidx.lifecycle.ViewModel
import com.mynimef.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class AuthMainViewModel @Inject constructor(
    authRepository: AuthRepository
): ViewModel() {

    val navigation = authRepository.navMain.asSharedFlow()

}