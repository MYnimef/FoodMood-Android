package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReportsViewModel: ViewModel() {
    private var job: Job? = null

    private val _coordinates = MutableStateFlow(emptyList<Coordinate>())
    val coordinate = _coordinates.asStateFlow()

    fun plot() {
        job = CoroutineScope(Dispatchers.IO).launch {
            delay(10000)
            _coordinates.value = listOf(
                Coordinate(0f, 5f),
                Coordinate(3f, 3f),
                Coordinate(5f, 1f),
                Coordinate(9f, 3f),
                Coordinate(15f, 2f),
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}

data class Coordinate(val x:Float, val y:Float)