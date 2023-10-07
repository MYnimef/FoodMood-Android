package com.mynimef.foodmood.presentation.screens.auth

import android.os.Build
import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.ApiError
import com.mynimef.foodmood.data.models.ApiException
import com.mynimef.foodmood.data.models.ApiSuccess
import com.mynimef.foodmood.data.models.enums.ENavAuth
import com.mynimef.foodmood.data.models.enums.EToast
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.repository.Repository
import com.mynimef.foodmood.domain.emailChecker
import com.mynimef.foodmood.domain.nameChecker
import com.mynimef.foodmood.domain.passwordChecker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class SignUpViewModel: ViewModel() {

    private var job: Job? = null

    private val _namePair = MutableStateFlow("" to true)
    val namePair = _namePair.asStateFlow()

    private val _birthday = MutableStateFlow("")
    val birthday = _birthday.asStateFlow()
    
    private val _firstButtonActive = MutableStateFlow(false)
    val firstButtonActive = _firstButtonActive.asStateFlow()

    private var birthdayCheck = false

    private val _food = MutableStateFlow(false)
    val food = _food.asStateFlow()

    private val _water = MutableStateFlow(false)
    val water = _water.asStateFlow()

    private val _weight = MutableStateFlow(false)
    val weight = _weight.asStateFlow()

    private val _emailPair = MutableStateFlow("" to true)
    val emailPair = _emailPair.asStateFlow()

    private val _passwordPair = MutableStateFlow("" to true)
    val passwordPair = _passwordPair.asStateFlow()

    private val _repeatPasswordPair = MutableStateFlow("" to true)
    val repeatPasswordPair = _repeatPasswordPair.asStateFlow()

    private val _secondButtonActive = MutableStateFlow(false)
    val secondButtonActive = _secondButtonActive.asStateFlow()

    private val _thirdButtonActive = MutableStateFlow(false)
    val thirdButtonActive = _thirdButtonActive.asStateFlow()

    fun setName(value: String) {
        val isValid = nameChecker(value)
        _namePair.value = value to isValid
        _firstButtonActive.value = isValid
    }

    fun setBirthday(value: String) {
        _birthday.value = value
        birthdayCheck = value.isNotEmpty() && (value != LocalDate.now().toString())
        checkSecondButtonActive()
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
        _emailPair.value = value to emailChecker(value)
        checkThirdButtonActive()
    }
    fun setPassword(value: String) {
        _passwordPair.value = value to passwordChecker(value)
        if (!repeatPasswordPair.value.second) {
            setRepeatPassword(repeatPasswordPair.value.first)
        }
        checkThirdButtonActive()
    }
    fun setRepeatPassword(value: String) {
        val isValid = value == passwordPair.value.first
        _repeatPasswordPair.value = value to isValid
        checkThirdButtonActive()
    }
    private fun checkThirdButtonActive() {
        _thirdButtonActive.value = emailPair.value.second && passwordPair.value.second && repeatPasswordPair.value.second
    }

    fun signUp() = with(Repository) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val request = SignUpRequest(
                name = _namePair.value.first,
                email = _emailPair.value.first,
                password = _passwordPair.value.first,
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