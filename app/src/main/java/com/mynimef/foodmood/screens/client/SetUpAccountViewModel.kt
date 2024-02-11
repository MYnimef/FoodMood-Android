package com.mynimef.foodmood.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetUpAccountViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    private var job: Job? = null

    fun signOut() = with(repository) {
        job = CoroutineScope(Dispatchers.IO).launch {
            signOut()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}