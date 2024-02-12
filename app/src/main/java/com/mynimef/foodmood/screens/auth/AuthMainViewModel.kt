package com.mynimef.foodmood.screens.auth

import androidx.lifecycle.ViewModel
import com.mynimef.data.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class AuthMainViewModel @Inject constructor(

): ViewModel() {

    val navigation = RepositoryImpl.authNavMain.asSharedFlow()

}