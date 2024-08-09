package com.mynimef.presentation.screens.client

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.domain.AppRepository
import com.mynimef.domain.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetUpAccountViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val clientRepository: ClientRepository
): ViewModel() {

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            val accountId = appRepository.getActualAccountId().stateIn(this).value
            clientRepository.signOut(accountId = accountId)
        }
    }

}