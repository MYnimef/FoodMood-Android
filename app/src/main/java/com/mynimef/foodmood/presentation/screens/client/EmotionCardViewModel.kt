package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.Emotion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EmotionCardViewModel: ViewModel() {
    private val _emotion = MutableStateFlow("")
    val emotion: StateFlow<String> = _emotion.asStateFlow()

    private val _textEmotion = MutableStateFlow("")
    val textEmotion: StateFlow<String> = _textEmotion.asStateFlow()

    private val _textFood = MutableStateFlow("")
    val textFood: StateFlow<String> = _textFood.asStateFlow()

    fun setTextEmotion(value: String) {
        _textEmotion.value = value
    }

    fun setTextFood(value: String) {
        _textFood.value = value
    }
}