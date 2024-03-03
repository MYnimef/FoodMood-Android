package com.mynimef.foodmood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.domain.AppRepository
import com.mynimef.domain.models.enums.EAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    appRepository: AppRepository
): ViewModel() {

    val appState = appRepository.getAppState().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = EAppState.AUTH
    )

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            delay(1500)
            _isLoading.value = false
        }
    }

}