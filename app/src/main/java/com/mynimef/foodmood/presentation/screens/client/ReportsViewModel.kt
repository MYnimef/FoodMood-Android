package com.mynimef.foodmood.presentation.screens.client

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.ApiError
import com.mynimef.foodmood.data.models.ApiException
import com.mynimef.foodmood.data.models.ApiSuccess
import com.mynimef.foodmood.data.models.enums.EToast
import com.mynimef.foodmood.data.models.enums.ETypePeriod
import com.mynimef.foodmood.data.models.requests.ClientDataRequest
import com.mynimef.foodmood.data.models.responses.DataResponse
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ReportsViewModel: ViewModel() {

    private var job: Job? = null

    private val _coordinatesEmotions = MutableStateFlow(emptyList<Pair<Float, Float>>())
    val coordinatesEmotions = _coordinatesEmotions.asStateFlow()

    private val _coordinatesWater = MutableStateFlow(emptyList<Pair<Float, Float>>())
    val coordinatesWater = _coordinatesWater.asStateFlow()

    private val _coordinatesWeight = MutableStateFlow(emptyList<Pair<Float, Float>>())
    val coordinatesWeight = _coordinatesWeight.asStateFlow()

    private val _period = MutableStateFlow(ETypePeriod.DAYS_7)
    val period = _period.asStateFlow()

    fun setPeriod(value: ETypePeriod) = with(Repository) {
        job = CoroutineScope(Dispatchers.IO).launch {
            _coordinatesEmotions.value = emptyList()
            _coordinatesWater.value = emptyList()
            _coordinatesWeight.value = emptyList()
            _period.value = value
            getDataFrom()
        }
    }

    fun getData() = with(Repository) {
        job = CoroutineScope(Dispatchers.IO).launch {
            getDataFrom()
        }
    }

    private suspend fun getDataFrom() = with(Repository) {
        val request = ClientDataRequest(
            timeZone = TimeZone.getDefault().id,
            days = _period.value.period.toLong(),
        )
        when (val result = network.clientGetData(request)) {
            is ApiError -> when (result.code) {
                401 ->  {
                    toastFlow.emit(EToast.AUTH_FAILED)
                    signOut()
                }
                else -> {}
            }
            is ApiException -> toastFlow.emit(EToast.NO_CONNECTION)
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

    private fun convertData(data: List<DataResponse>): List<Pair<Float, Float>> {
        val max = _period.value.period
        val actualDate = LocalDate.now()

        val result = mutableListOf<Pair<Float, Float>>()
        data.forEach {
            val dataDate = LocalDate.of(it.year, it.month, it.day)
            val diff = ChronoUnit.DAYS.between(dataDate, actualDate).toInt()

            result.add(Pair((max - diff).toFloat(), it.value))
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}