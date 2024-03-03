package com.mynimef.foodmood.screens.client

import android.icu.util.TimeZone
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.AppRepository
import com.mynimef.domain.ClientRepository
import com.mynimef.domain.models.ClientModel
import com.mynimef.domain.models.enums.ENavClientMain
import com.mynimef.domain.models.enums.ETypeEmotion
import com.mynimef.domain.models.enums.ETypeMeal
import com.mynimef.domain.models.requests.IClientAddCardRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val clientRepository: ClientRepository
): ViewModel() {

    private val actualAccountId = appRepository.getActualAccountId().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = 0L
    )

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
        viewModelScope.launch(Dispatchers.IO) {
            _client.value = clientRepository.getAccount(accountId = actualAccountId.value)
        }
    }

    fun setMealType(value: ETypeMeal) {
        _mealType.value = value
        viewModelScope.launch(Dispatchers.Main) {
            clientRepository.navMain.emit(ENavClientMain.CREATE_EMOTION_CARD)
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

    fun create() {
        viewModelScope.launch(Dispatchers.IO) {
            val accountId = actualAccountId.value
            val request = IClientAddCardRequest.create(
                mealType = _mealType.value,
                emotionType = _emotionType.value,
                emotionDescription = _emotionDescription.value,
                foodDescription = _foodDescription.value,
                timeZone = TimeZone.getDefault().id,
            )
            when (val result = clientRepository.addCard(
                accountId = accountId,
                request = request
            )) {
                is ApiError -> {
                    when (result.code) {
                        401 -> appRepository.signOut(accountId = accountId)
                        else -> {}
                    }
                }
                is ApiException -> {}
                is ApiSuccess -> {
                    clientRepository.insertCard(result.data)
                    clientRepository.navMain.emit(ENavClientMain.BOTTOM_BAR)
                }
            }
        }
    }

}