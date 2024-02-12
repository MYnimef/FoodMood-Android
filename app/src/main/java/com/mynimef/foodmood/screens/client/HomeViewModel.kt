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
import com.mynimef.domain.UpdatableAccount
import com.mynimef.domain.models.requests.IClientInfoRequest
import com.mynimef.domain.models.requests.IWaterIncreaseRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    private val updatableAccount = UpdatableAccount(
        repository = repository,
        scope = viewModelScope
    )
    private val actualAccountId = updatableAccount.actualAccountId
    val client = updatableAccount.getClient()

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

    val cards = repository.storage.getAllCards()
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun setWater(amount: Float): Unit = with(repository) {
        viewModelScope.launch(Dispatchers.IO) {
            val accountId = actualAccountId.value
            val request = IWaterIncreaseRequest.create(
                amount = amount,
                timeZone = TimeZone.getDefault().id,
            )

            when (val result = network.clientIncreaseWater(
                accountId = accountId,
                request = request
            )) {
                is ApiError -> {
                    when (result.code) {
                        401 -> signOutClient(accountId = accountId)
                        else -> {}
                    }
                }
                is ApiException -> RepositoryImpl.toastFlow.emit(EToast.NO_CONNECTION)
                is ApiSuccess -> {
                    storage.updateWaterAmountClient(
                        accountId = accountId,
                        waterAmount = result.data.totalAmount
                    )
                    updatableAccount.update()
                }
            }
        }
    }

    suspend fun update() = with(repository) {
        val accountId = actualAccountId.value
        val request = IClientInfoRequest.create(
            timeZone = TimeZone.getDefault().id
        )
        when (val result = network.clientGetInfo(
            accountId = accountId,
            request = request
        )) {
            is ApiError -> {
                when (result.code) {
                    401 -> {
                        RepositoryImpl.toastFlow.emit(EToast.AUTH_FAILED)
                        signOutClient(accountId = accountId)
                    }
                    else -> {}
                }
            }
            is ApiException -> RepositoryImpl.toastFlow.emit(EToast.NO_CONNECTION)
            is ApiSuccess -> {
                val data = result.data
                storage.deleteAllCards()
                data.dayCards.forEach {
                    storage.insertCard(it)
                }
                storage.insertClient(accountId = accountId, client = data)
                updatableAccount.update()
            }
        }
    }

}