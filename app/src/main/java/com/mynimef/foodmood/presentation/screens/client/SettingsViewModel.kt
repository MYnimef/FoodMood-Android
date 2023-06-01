package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.ENavClientMain
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SettingsViewModel: ViewModel() {

    private var job: Job? = null

    fun navigateTo(nav: ENavClientMain) = with(Repository) {
        job = CoroutineScope(Dispatchers.Main).launch {
            clientNavMain.emit(nav)
        }
    }

}
