package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    private val _water = MutableStateFlow(0f)
    val water: StateFlow<Float> = _water.asStateFlow()

    fun setWater(value: Float) {
        _water.value += value
    }
}