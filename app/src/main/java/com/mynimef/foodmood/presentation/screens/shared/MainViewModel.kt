package com.mynimef.foodmood.presentation.screens.shared

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {
    fun getAppState(): StateFlow<EAppState> {
        return Repository.appState
    }
}