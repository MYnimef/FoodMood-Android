package com.mynimef.foodmood.presentation.screens.shared

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.repository.Repository

class MainNavigationViewModel: ViewModel() {
    fun getAppState() = Repository.appState
}