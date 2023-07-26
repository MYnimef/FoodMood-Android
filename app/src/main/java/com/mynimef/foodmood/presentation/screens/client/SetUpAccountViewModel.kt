package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SetUpAccountViewModel: ViewModel() {

    private var job: Job? = null

    fun signOut() = with(Repository) {
        job = CoroutineScope(Dispatchers.IO).launch {
            signOut()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}