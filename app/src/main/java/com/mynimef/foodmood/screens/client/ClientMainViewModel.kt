package com.mynimef.foodmood.screens.client

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.AppRepository
import com.mynimef.domain.ClientRepository
import com.mynimef.domain.models.requests.IClientInfoRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientMainViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val clientRepository: ClientRepository
): ViewModel() {

    val navigation = clientRepository.navMain.asSharedFlow()

    fun initClient() {
        viewModelScope.launch(Dispatchers.IO) {
            val accountId = appRepository.getActualAccountId().stateIn(this).value

            when (val result = clientRepository.getInfo(
                accountId = accountId,
                request = IClientInfoRequest.create(TimeZone.getDefault().id)
            )) {
                is ApiError -> when (result.code) {
                    401 ->  {
                        appRepository.signOut(accountId = accountId)
                    }
                    else -> {}
                }
                is ApiException -> {}
                is ApiSuccess -> {
                    val data = result.data
                    clientRepository.deleteAllCards()
                    data.dayCards.forEach {
                        clientRepository.insertCard(it)
                    }
                    clientRepository.insertAccount(
                        accountId = accountId,
                        client = data
                    )
                }
            }
        }
    }

}