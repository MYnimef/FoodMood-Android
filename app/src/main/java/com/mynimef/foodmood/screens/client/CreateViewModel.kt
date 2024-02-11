package com.mynimef.foodmood.screens.client

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import com.mynimef.data.RepositoryImpl
import com.mynimef.data.enums.ENavClientMain
import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.AppRepository
import com.mynimef.domain.models.ClientModel
import com.mynimef.domain.models.ETypeEmotion
import com.mynimef.domain.models.ETypeMeal
import com.mynimef.domain.models.requests.IClientAddCardRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

    private var job: Job? = null

    private val _mealType = MutableStateFlow(ETypeMeal.BREAKFAST)
    val mealType = _mealType.asStateFlow()

    private val _emotionType = MutableStateFlow(ETypeEmotion.NORMAL)
    val emotionType = _emotionType.asStateFlow()

    private val _emotionDescription = MutableStateFlow("")
    val emotionDescription = _emotionDescription.asStateFlow()

    private val _foodDescription = MutableStateFlow("")
    val foodDescription = _foodDescription.asStateFlow()

    private val _buttonCompleteActive = MutableStateFlow(false)
    val buttonCompleteActive = _buttonCompleteActive.asStateFlow()

    private val _weight = MutableStateFlow(0.0f)
    val weight: StateFlow<Float> = _weight.asStateFlow()

    private val _isDialogShown = MutableStateFlow(false)
    val isDialogShown = _isDialogShown.asStateFlow()

    fun triggerDialogShown(){
        _isDialogShown.value = !_isDialogShown.value
    }

    private val _client = MutableStateFlow<ClientModel?>(null)
    val client = _client.asStateFlow()

    init {
        job = CoroutineScope(Dispatchers.IO).launch {
            _client.value = repository.storage.getClient()
        }
    }

    fun setMealType(value: ETypeMeal) {
        _mealType.value = value
        job = CoroutineScope(Dispatchers.Main).launch {
            RepositoryImpl.clientNavMain.emit(ENavClientMain.CREATE_EMOTION_CARD)
        }
    }

    fun setEmotionType(value: ETypeEmotion) {
        _emotionType.value = value
    }

    fun setEmotionDescription(value: String) {
        _emotionDescription.value = value
        _buttonCompleteActive.value = value.isNotEmpty()
    }

    fun setFoodDescription(value: String) {
        _foodDescription.value = value
    }

    fun setWeight(value: Float) {
        _weight.value = value
    }

    fun create() = with(repository) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val request = IClientAddCardRequest.create(
                mealType = _mealType.value,
                emotionType = _emotionType.value,
                emotionDescription = _emotionDescription.value,
                foodDescription = _foodDescription.value,
                timeZone = TimeZone.getDefault().id,
            )
            when (val result = network.clientAddCard(request)) {
                is ApiError -> {
                    when (result.code) {
                        401 -> signOut()
                        else -> {}
                    }
                }
                is ApiException -> {}
                is ApiSuccess -> {
                    storage.insertCard(result.data)
                    RepositoryImpl.clientNavMain.emit(ENavClientMain.BOTTOM_BAR)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}