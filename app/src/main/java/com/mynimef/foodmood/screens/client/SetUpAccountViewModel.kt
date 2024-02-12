package com.mynimef.foodmood.screens.client

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetUpAccountViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    fun signOut() = with(repository) {
        viewModelScope.launch(Dispatchers.IO) {
            val accountId = repository.getActualAccountId().stateIn(this).value
            signOutClient(accountId = accountId)
        }
    }

}