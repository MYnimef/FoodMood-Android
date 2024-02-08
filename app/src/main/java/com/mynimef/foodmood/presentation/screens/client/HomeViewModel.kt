package com.mynimef.foodmood.presentation.screens.client

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.foodmood.data.models.ApiError
import com.mynimef.foodmood.data.models.ApiException
import com.mynimef.foodmood.data.models.ApiSuccess
import com.mynimef.foodmood.data.models.enums.EToast
import com.mynimef.foodmood.data.models.requests.ClientInfoRequest
import com.mynimef.foodmood.data.models.requests.WaterIncreaseRequest
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var job: Job? = null

    private val client = Repository.storage.getClient()
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

    val cards = Repository.storage.getAllCards()
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun setWater(amount: Float) = with(Repository) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val request = WaterIncreaseRequest(
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

    suspend fun update() = with(Repository) {
        val request = ClientInfoRequest(
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
                    storage.insertCard(it.toCardEntity())
                }
                storage.insertClient(data.toClientEntity())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}