package com.mynimef.foodmood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.data.RepositoryImpl
import com.mynimef.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    val toastFlow = RepositoryImpl.toastFlow.asSharedFlow()
    val appState = repository.appState

    private val _isLoading = MutableStateFlow(true);
    val isLoading = _isLoading.asStateFlow();

    init {
        viewModelScope.launch(Dispatchers.Main) {
            delay(1500)
            _isLoading.value = false
        }
    }

}