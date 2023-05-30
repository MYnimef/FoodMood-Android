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

    private val _coordinates = MutableStateFlow(emptyList<Pair<Float, Float>>())
    val coordinate = _coordinates.asStateFlow()

    private val _period = MutableStateFlow(ETypePeriod.DAYS_7)
    val period = _period.asStateFlow()

    fun setPeriod(value: ETypePeriod) {
        _period.value = value
    }

    fun plot() {
        job = CoroutineScope(Dispatchers.IO).launch {
            delay(10000)
            _coordinates.value = listOf(
                Pair(0f, 5f),
                Pair(3f, 3f),
                Pair(5f, 1f),
                Pair(9f, 3f),
                Pair(15f, 2f),
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}