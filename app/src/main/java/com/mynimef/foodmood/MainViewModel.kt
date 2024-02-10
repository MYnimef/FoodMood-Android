package com.mynimef.foodmood

import android.content.Context
import androidx.lifecycle.ViewModel
import com.mynimef.data.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var job: Job? = null

    val toastFlow = RepositoryImpl.toastFlow.asSharedFlow()
    val appState = RepositoryImpl.appState

    private val _dataLoaded = MutableStateFlow(false)
    val dataLoaded = _dataLoaded.asStateFlow()

    fun initData(context: Context) = with(RepositoryImpl) {
        job = CoroutineScope(Dispatchers.IO).launch {
            init(context)
            _dataLoaded.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}