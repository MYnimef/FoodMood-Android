package com.mynimef.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.domain.AppRepository
import com.mynimef.domain.models.enums.EAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class ClientViewModel @Inject constructor(
    appRepository: AppRepository
): ViewModel() {

    val appState = appRepository.getAppState().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = EAppState.CLIENT
    )

}