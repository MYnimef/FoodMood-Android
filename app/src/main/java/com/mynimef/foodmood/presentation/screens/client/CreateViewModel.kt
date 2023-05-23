package com.mynimef.foodmood.presentation.screens.client

import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.ECallback
import com.mynimef.foodmood.data.models.enums.ENavigationCreate
import com.mynimef.foodmood.data.models.enums.ETypeEmotion
import com.mynimef.foodmood.data.models.enums.ETypeMeal
import com.mynimef.foodmood.data.models.requests.ClientAddCardRequest
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateViewModel: ViewModel() {

    private var job: Job? = null

    lateinit var navigation: (ENavigationCreate) -> Unit

    private val _mealType = MutableStateFlow(ETypeMeal.BREAKFAST)
    val mealType: StateFlow<ETypeMeal> = _mealType.asStateFlow()

    private val _emotionType = MutableStateFlow(ETypeEmotion.NORMAL)
    val emotionType: StateFlow<ETypeEmotion> = _emotionType.asStateFlow()

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

    fun create() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val request = ClientAddCardRequest(
                mealType = _mealType.value,
                emotionType = _emotionType.value,
                emotionDescription = emotion.value,
                foodDescription = _textFood.value,
            )
            val response = Repository.clientAddCard(request)
            withContext(Dispatchers.Main) {
                when (response) {
                    ECallback.SUCCESS -> {}
                    ECallback.UNAUTHORIZED -> {}
                    ECallback.NO_CONNECTION -> {}
                    else -> {}
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}