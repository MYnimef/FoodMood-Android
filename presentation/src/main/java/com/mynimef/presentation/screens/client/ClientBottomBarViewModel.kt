package com.mynimef.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.domain.ClientRepository
import com.mynimef.domain.models.enums.ENavClientBottomBar
import com.mynimef.domain.models.enums.ENavClientMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientBottomBarViewModel @Inject constructor(
    private val clientRepository: ClientRepository
): ViewModel() {

    private var job: Job? = null

    val navigation = clientRepository.navBottomBar.asSharedFlow()

    fun switchScreen(nav: ENavClientBottomBar) {
        job = CoroutineScope(Dispatchers.Main).launch {
            clientRepository.navBottomBar.emit(nav)
        }
    }

    fun switchScreen(nav: ENavClientMain) {
        job = CoroutineScope(Dispatchers.Main).launch {
            clientRepository.navMain.emit(nav)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}