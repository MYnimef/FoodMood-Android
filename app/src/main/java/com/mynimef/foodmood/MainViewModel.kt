package com.mynimef.foodmood

import androidx.lifecycle.ViewModel
import com.mynimef.data.RepositoryImpl
import com.mynimef.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    private var job: Job? = null

    val toastFlow = RepositoryImpl.toastFlow.asSharedFlow()
    val appState = repository.appState

    private val _dataLoaded = MutableStateFlow(false)
    val dataLoaded = _dataLoaded.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}