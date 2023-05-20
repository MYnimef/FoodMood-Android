package com.mynimef.foodmood.presentation.screens.shared

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.data.repository.Repository

class MainViewModel: ViewModel() {
    fun getAppState(): EAppState {
        return Repository.appState
    }
}