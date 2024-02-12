package com.mynimef.foodmood.screens.client

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.data.RepositoryImpl
import com.mynimef.data.enums.EToast
import com.mynimef.data.enums.ETypePeriod
import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.AppRepository
import com.mynimef.domain.models.requests.IClientDataRequest
import com.mynimef.domain.models.responses.IDataResponse
import com.mynimef.foodmood.extensions.getNum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    private val actualAccountId = repository.getActualAccountId().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = 0L
    )

    private val _coordinatesEmotions = MutableStateFlow(emptyList<Pair<Float, Float>>())
    val coordinatesEmotions = _coordinatesEmotions.asStateFlow()

    private val _coordinatesWater = MutableStateFlow(emptyList<Pair<Float, Float>>())
    val coordinatesWater = _coordinatesWater.asStateFlow()

    private val _coordinatesWeight = MutableStateFlow(emptyList<Pair<Float, Float>>())
    val coordinatesWeight = _coordinatesWeight.asStateFlow()

    private val _period = MutableStateFlow(ETypePeriod.DAYS_7)
    val period = _period.asStateFlow()

    init {
        getData()
    }

    fun setPeriod(value: ETypePeriod) {
        _period.value = value
        getData()
    }

    fun getData() = with(repository) {
        viewModelScope.launch(Dispatchers.IO) {
            val accountId = actualAccountId.value
            val request = IClientDataRequest.create(
                timeZone = TimeZone.getDefault().id,
                days = _period.value.getNum().toLong(),
            )
            when (val result = network.clientGetData(
                accountId = accountId,
                request = request
            )) {
                is ApiError -> when (result.code) {
                    401 -> {
                        RepositoryImpl.toastFlow.emit(EToast.AUTH_FAILED)
                        signOutClient(accountId = accountId)
                    }
                    else -> {}
                }
                is ApiException -> RepositoryImpl.toastFlow.emit(EToast.NO_CONNECTION)
                is ApiSuccess -> {
                    val data = result.data
                    _coordinatesEmotions.value = convertData(data.emotionData)
                    data.waterData?.let {
                        _coordinatesWater.value = convertData(it)
                    }
                    data.weightData?.let {
                        _coordinatesWeight.value = convertData(it)
                    }
                }
            }
        }
    }

    private fun convertData(data: List<IDataResponse>): List<Pair<Float, Float>> {
        val max = _period.value.getNum()
        val actualDate = LocalDate.now()

        val result = mutableListOf<Pair<Float, Float>>()
        data.forEach {
            val dataDate = LocalDate.of(it.year, it.month, it.day)
            val diff = ChronoUnit.DAYS.between(dataDate, actualDate).toInt()

            result.add(Pair((max - diff).toFloat(), it.value))
        }
        return result
    }

}