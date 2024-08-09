package com.mynimef.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.domain.ClientRepository
import com.mynimef.domain.models.enums.ENavClientMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val clientRepository: ClientRepository
): ViewModel() {

    private var job: Job? = null

    fun navigateTo(nav: ENavClientMain) {
        job = CoroutineScope(Dispatchers.Main).launch {
            clientRepository.navMain.emit(nav)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
