package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {

    private val _water = MutableStateFlow(0f)
    val water = _water.asStateFlow()

    fun setWater(value: Float) {
        _water.value += value
    }

    fun getCards() = Repository.getCardsFromStorage()

}