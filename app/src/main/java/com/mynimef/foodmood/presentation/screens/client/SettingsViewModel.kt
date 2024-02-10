package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.data.enums.ENavClientMain
import com.mynimef.data.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SettingsViewModel: ViewModel() {

    private var job: Job? = null

    fun navigateTo(nav: ENavClientMain) = with(RepositoryImpl) {
        job = CoroutineScope(Dispatchers.Main).launch {
            clientNavMain.emit(nav)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
