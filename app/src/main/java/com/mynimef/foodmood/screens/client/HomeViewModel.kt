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
import com.mynimef.domain.models.ClientModel
import com.mynimef.domain.models.requests.IClientInfoRequest
import com.mynimef.domain.models.requests.IWaterIncreaseRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    private val _client = MutableStateFlow<ClientModel?>(null)
    val client = _client.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _client.value = repository.storage.getClient()
        }
    }

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

    fun setWater(amount: Float) = with(repository) {
        viewModelScope.launch(Dispatchers.IO) {
            val request = IWaterIncreaseRequest.create(
                amount = amount,
                TimeZone.getDefault().id,
            )
            when (val result = network.clientIncreaseWater(request)) {
                is ApiError -> {
                    when (result.code) {
                        401 -> signOut()
                        else -> {}
                    }
                }
                is ApiException -> {}
                is ApiSuccess -> {
                    storage.updateWaterAmountClient(result.data.totalAmount)
                    _client.value = repository.storage.getClient()
                }
            }
        }
    }

    suspend fun update() = with(repository) {
        val request = IClientInfoRequest.create(
            timeZone = TimeZone.getDefault().id
        )
        when (val result = network.clientGetInfo(request)) {
            is ApiError -> {
                when (result.code) {
                    401 -> {
                        RepositoryImpl.toastFlow.emit(EToast.AUTH_FAILED)
                        signOut()
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
                storage.insertClient(data)
                _client.value = repository.storage.getClient()
            }
        }
    }

}