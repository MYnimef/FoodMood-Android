package com.mynimef.foodmood.presentation.screens.client

import android.icu.util.TimeZone
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

    private val _emotionDescription = MutableStateFlow("")
    val emotionDescription: StateFlow<String> = _emotionDescription.asStateFlow()

    private val _foodDescription = MutableStateFlow("")
    val foodDescription: StateFlow<String> = _foodDescription.asStateFlow()

    fun setMealType(value: ETypeMeal) {
        _mealType.value = value
        navigation(ENavigationCreate.ADD_CARD)
    }

    fun setTextEmotion(value: String) {
        _emotionDescription.value = value
    }

    fun setTextFood(value: String) {
        _foodDescription.value = value
    }

    fun create() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val request = ClientAddCardRequest(
                mealType = _mealType.value,
                emotionType = _emotionType.value,
                emotionDescription = _emotionDescription.value,
                foodDescription = _foodDescription.value,
                timeZone = TimeZone.getDefault().id,
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