package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.ENavigationCreate
import com.mynimef.foodmood.data.models.enums.ETypeMeal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateViewModel: ViewModel() {

    lateinit var navigation: (ENavigationCreate) -> Unit

    private val _mealType = MutableStateFlow(ETypeMeal.BREAKFAST)
    val mealType: StateFlow<ETypeMeal> = _mealType.asStateFlow()

    private val _emotion = MutableStateFlow("")
    val emotion: StateFlow<String> = _emotion.asStateFlow()

    private val _textEmotion = MutableStateFlow("")
    val textEmotion: StateFlow<String> = _textEmotion.asStateFlow()

    private val _textFood = MutableStateFlow("")
    val textFood: StateFlow<String> = _textFood.asStateFlow()

    fun setMealType(value: ETypeMeal) {
        _mealType.value = value
        navigation(ENavigationCreate.ADD_CARD)
    }

    fun setTextEmotion(value: String) {
        _textEmotion.value = value
    }

    fun setTextFood(value: String) {
        _textFood.value = value
    }
}