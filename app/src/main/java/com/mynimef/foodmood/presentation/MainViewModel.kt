package com.mynimef.foodmood.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private var job: Job? = null

    val toastFlow = Repository.getToastFlow()

    private val _dataLoaded = MutableStateFlow(false)
    val dataLoaded = _dataLoaded.asStateFlow()

    fun initData(context: Context) {
        job = CoroutineScope(Dispatchers.IO).launch {
            Repository.init(context)
            _dataLoaded.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}