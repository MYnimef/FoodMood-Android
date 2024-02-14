package com.mynimef.foodmood.screens.client

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.data.RepositoryImpl
import com.mynimef.data.enums.EToast
import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.AppRepository
import com.mynimef.domain.ClientRepository
import com.mynimef.domain.UpdatableAccount
import com.mynimef.domain.models.ClientModel
import com.mynimef.domain.models.requests.IClientInfoRequest
import com.mynimef.domain.models.requests.IWaterIncreaseRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AppRepository,
    private val clientRepository: ClientRepository
): ViewModel() {

    private val actualAccountId: StateFlow<Long>
    private val _updateFlow: MutableStateFlow<Boolean>
    val client: StateFlow<ClientModel?>

    init { UpdatableAccount(
        repository = repository,
        scope = viewModelScope
    ).let {
        actualAccountId = it.actualAccountId
        _updateFlow = it.updateFlow
        client = it.getClient(clientRepository)
    } }

    val updateFlow = _updateFlow.asStateFlow()

    val dataLoaded = client.map {
        it != null
    }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )

    val trackWater = client.map {
        it?.trackWater ?: false
    }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )

    val waterAmount = client.map {
        it?.waterAmount ?: 0f
    }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = 0f
        )

    val cards = clientRepository.getAllCards()
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    private fun update(
        upd: suspend () -> Unit
    ) = viewModelScope.launch(Dispatchers.IO) {
        _updateFlow.value = true
        upd()
        _updateFlow.value = false
    }

    fun setWater(amount: Float) {
        update { updateWater(amount) }
    }

    fun updateAccount() {
        update(::downloadAccount)
    }

    private suspend fun updateWater(amount: Float) = with(repository) {
        val accountId = actualAccountId.value
        val request = IWaterIncreaseRequest.create(
            amount = amount,
            timeZone = TimeZone.getDefault().id,
        )

        when (val result = clientRepository.increaseWater(
            accountId = accountId,
            request = request
        )) {
            is ApiError -> {
                when (result.code) {
                    401 -> clientRepository.signOut(accountId = accountId)
                    else -> {}
                }
            }
            is ApiException -> RepositoryImpl.toastFlow.emit(EToast.NO_CONNECTION)
            is ApiSuccess -> {
                clientRepository.updateWaterAmount(
                    accountId = accountId,
                    waterAmount = result.data.totalAmount
                )
            }
        }
    }

    private suspend fun downloadAccount() = with(repository) {
        val accountId = actualAccountId.value
        val request = IClientInfoRequest.create(
            timeZone = TimeZone.getDefault().id
        )
        when (val result = clientRepository.getInfo(
            accountId = accountId,
            request = request
        )) {
            is ApiError -> {
                when (result.code) {
                    401 -> {
                        RepositoryImpl.toastFlow.emit(EToast.AUTH_FAILED)
                        clientRepository.signOut(accountId = accountId)
                    }
                    else -> {}
                    }
            }
            is ApiException -> RepositoryImpl.toastFlow.emit(EToast.NO_CONNECTION)
            is ApiSuccess -> {
                val data = result.data
                clientRepository.deleteAllCards()
                data.dayCards.forEach {
                    clientRepository.insertCard(it)
                }
                clientRepository.insertAccount(accountId = accountId, client = data)
            }
        }
    }

}