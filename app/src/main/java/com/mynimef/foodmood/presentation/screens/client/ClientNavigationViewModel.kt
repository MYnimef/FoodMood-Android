package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.ECallback
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClientNavigationViewModel: ViewModel() {

    private var job: Job? = null

    fun initClient() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = Repository.getClient()
            withContext(Dispatchers.Main) {
                when (response) {
                    ECallback.SUCCESS -> {}
                    ECallback.NO_CONNECTION -> {}
                    else -> {}
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}