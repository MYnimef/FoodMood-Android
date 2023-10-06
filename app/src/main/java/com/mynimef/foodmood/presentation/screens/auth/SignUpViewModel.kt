package com.mynimef.foodmood.presentation.screens.auth

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.foodmood.data.models.ApiError
import com.mynimef.foodmood.data.models.ApiException
import com.mynimef.foodmood.data.models.ApiSuccess
import com.mynimef.foodmood.data.models.enums.ENavAuth
import com.mynimef.foodmood.data.models.enums.EToast
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class SignUpViewModel: ViewModel() {

    private var job: Job? = null

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _birthday = MutableStateFlow("")
    val birthday = _birthday.asStateFlow()
    
    private val _firstButtonActive = MutableStateFlow(false)
    val firstButtonActive = _firstButtonActive.asStateFlow()

    private var nameCheck = false
    private var birthdayCheck = false

    private val _food = MutableStateFlow(false)
    val food = _food.asStateFlow()

    private val _water = MutableStateFlow(false)
    val water = _water.asStateFlow()

    private val _weight = MutableStateFlow(false)
    val weight = _weight.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _passwordValues = MutableStateFlow("" to false)
    val passwordValues = _passwordValues.asStateFlow()

    private val _repeatPasswordValues = MutableStateFlow("" to false)
    val repeatPasswordValues = _repeatPasswordValues.asStateFlow()

    val thirdButtonActive = combine(passwordValues, repeatPasswordValues) { f1, f2 ->
        !(f1.second || f2.second)
    }
        .distinctUntilChanged()
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )

    private val _secondButtonActive = MutableStateFlow(false)
    val secondButtonActive = _secondButtonActive.asStateFlow()

    private var emailCheck = false

    fun setName(value: String) {
        _name.value = value
        nameCheck = value.length >= 2
        checkFirstButtonActive()
    }
    fun setBirthday(value: String) {
        _birthday.value = value
        birthdayCheck = value.isNotEmpty() && (value != LocalDate.now().toString())
        checkSecondButtonActive()
    }
    private fun checkFirstButtonActive() {
        _firstButtonActive.value = nameCheck
    }

    private fun checkSecondButtonActive() {
        _secondButtonActive.value = birthdayCheck
    }

    fun triggerFood() {
        _food.value = !_food.value
    }
    fun triggerWater() {
        _water.value = !_water.value
    }
    fun triggerWeight() {
        _weight.value = !_weight.value
    }

    fun setEmail(value: String) {
        _email.value = value
        emailCheck = value.isNotEmpty()
    }
    fun setPassword(value: String) {
        val isError = value.length < 8
        _passwordValues.value = value to isError
    }
    fun setRepeatPassword(value: String) {
        val isError = value.length < 8 || value != passwordValues.value.first
        _repeatPasswordValues.value = value to isError
    }

    fun signUp() = with(Repository) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val request = SignUpRequest(
                name = _name.value,
                email = _email.value,
                password = _passwordValues.value.first,
                trackFood = _food.value,
                trackWater = _water.value,
                trackWeight = _weight.value,
                device = Build.MANUFACTURER + " " + Build.MODEL,
            )
            when (val result = network.signUpClient(request)) {
                is ApiError -> {
                    when (result.code) {
                        403 -> toastFlow.emit(EToast.WRONG_INPUT)
                        409 -> toastFlow.emit(EToast.ACCOUNT_ALREADY_EXISTS)
                        else -> {}
                    }
                }
                is ApiException -> toastFlow.emit(EToast.NO_CONNECTION)
                is ApiSuccess -> signIn(result.data)
            }
        }
    }

    fun navigateTo(nav: ENavAuth) = with(Repository) {
        job = CoroutineScope(Dispatchers.Main).launch {
            authNavMain.emit(nav)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}