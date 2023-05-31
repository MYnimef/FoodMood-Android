package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.ETypePeriod
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReportsViewModel: ViewModel() {
    private var job: Job? = null

    private val _coordinatesEmotions = MutableStateFlow(emptyList<Pair<Float, Float>>())
    val coordinatesEmotions = _coordinatesEmotions.asStateFlow()

    private val _coordinatesWater = MutableStateFlow(emptyList<Pair<Float, Float>>())
    val coordinatesWater = _coordinatesWater.asStateFlow()


    private val _period = MutableStateFlow(ETypePeriod.DAYS_7)
    val period = _period.asStateFlow()

    fun setPeriod(value: ETypePeriod) {
        _period.value = value
    }

    fun plot() {
        job = CoroutineScope(Dispatchers.IO).launch {
            delay(10000)
            _coordinatesEmotions.value = listOf(
                Pair(0f, 5f),
                Pair(3f, 3f),
                Pair(5f, 1f),
                Pair(9f, 3f),
                Pair(15f, 2f),
            )
            _coordinatesWater.value = listOf(
                Pair(0f, 1000f),
                Pair(3f, 200f),
                Pair(5f, 100f),
                Pair(9f, 2000f),
                Pair(15f, 500f),
            )
            _coordinatesWater.value = listOf(
                Pair(0f, 1000f),
                Pair(3f, 200f),
                Pair(5f, 100f),
                Pair(9f, 2000f),
                Pair(15f, 500f),
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}