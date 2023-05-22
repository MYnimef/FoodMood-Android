package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.EFoodType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateViewModel: ViewModel() {
    private val _foodType = MutableStateFlow(EFoodType.NONE)
    val foodType: StateFlow<EFoodType> = _foodType.asStateFlow()

    fun setFoodType(value: EFoodType) {
        _foodType.value = value
    }
}