package com.mynimef.foodmood.screens.client

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.models.requests.IClientInfoRequest
import com.mynimef.domain.models.requests.IWaterIncreaseRequest
import com.mynimef.data.enums.EToast
import com.mynimef.data.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var job: Job? = null

    private val client = RepositoryImpl.storage.getClient()
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )

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

    val cards = RepositoryImpl.storage.getAllCards()
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun setWater(amount: Float) = with(RepositoryImpl) {
        job = CoroutineScope(Dispatchers.IO).launch {
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
                }
            }
        }
    }

    suspend fun update() = with(RepositoryImpl) {
        val request = IClientInfoRequest.create(
            timeZone = TimeZone.getDefault().id
        )
        when (val result = network.clientGetInfo(request)) {
            is ApiError -> {
                when (result.code) {
                    401 -> {
                        toastFlow.emit(EToast.AUTH_FAILED)
                        signOut()
                    }
                    else -> {}
                }
            }
            is ApiException -> toastFlow.emit(EToast.NO_CONNECTION)
            is ApiSuccess -> {
                val data = result.data
                storage.deleteAllCards()
                data.dayCards.forEach {
                    storage.insertCard(it)
                }
                storage.insertClient(data.client)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}