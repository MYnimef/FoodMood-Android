package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.ENavClientBottomBar
import com.mynimef.foodmood.data.models.enums.ENavClientMain
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ClientBottomBarViewModel: ViewModel() {

    private var job: Job? = null

    val navigation = Repository.clientNavBottomBar.asSharedFlow()

    fun switchScreen(nav: ENavClientBottomBar) {
        job = CoroutineScope(Dispatchers.Main).launch {
            Repository.clientNavBottomBar.emit(nav)
        }
    }

    fun switchScreen(nav: ENavClientMain) {
        job = CoroutineScope(Dispatchers.Main).launch {
            Repository.clientNavMain.emit(nav)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}