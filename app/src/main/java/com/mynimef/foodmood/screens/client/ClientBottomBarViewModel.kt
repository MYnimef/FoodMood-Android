package com.mynimef.foodmood.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.data.enums.ENavClientBottomBar
import com.mynimef.data.enums.ENavClientMain
import com.mynimef.data.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientBottomBarViewModel @Inject constructor(

): ViewModel() {

    private var job: Job? = null

    val navigation = RepositoryImpl.clientNavBottomBar.asSharedFlow()

    fun switchScreen(nav: ENavClientBottomBar) {
        job = CoroutineScope(Dispatchers.Main).launch {
            RepositoryImpl.clientNavBottomBar.emit(nav)
        }
    }

    fun switchScreen(nav: ENavClientMain) {
        job = CoroutineScope(Dispatchers.Main).launch {
            RepositoryImpl.clientNavMain.emit(nav)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}