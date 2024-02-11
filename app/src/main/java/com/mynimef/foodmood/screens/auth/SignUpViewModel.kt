package com.mynimef.foodmood.screens.auth

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiSuccess
import com.mynimef.data.enums.ENavAuth
import com.mynimef.data.enums.EToast
import com.mynimef.data.RepositoryImpl
import com.mynimef.domain.AppRepository
import com.mynimef.domain.extensions.emailChecker
import com.mynimef.domain.extensions.nameChecker
import com.mynimef.domain.extensions.passwordChecker
import com.mynimef.domain.models.requests.ISignUpRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {

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

    val thirdButtonActive = combine(
        emailPair,
        passwordPair,
        repeatPasswordPair
    ) { f1, f2, f3 ->
        f1.second && f2.second && f3.second && f1.first.isNotEmpty() && f2.first.isNotEmpty() && f3.first.isNotEmpty()
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )

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
    }
    fun setPassword(value: String) {
        _passwordPair.value = value to passwordChecker(value)
        if (!repeatPasswordPair.value.second) {
            setRepeatPassword(repeatPasswordPair.value.first)
        }
    }
    fun setRepeatPassword(value: String) {
        val isValid = value == passwordPair.value.first
        _repeatPasswordPair.value = value to isValid
    }

    fun signUp() = with(repository) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val request = ISignUpRequest.create(
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
                        403 -> RepositoryImpl.toastFlow.emit(EToast.WRONG_INPUT)
                        409 -> RepositoryImpl.toastFlow.emit(EToast.ACCOUNT_ALREADY_EXISTS)
                        else -> {}
                    }
                }
                is ApiException -> RepositoryImpl.toastFlow.emit(EToast.NO_CONNECTION)
                is ApiSuccess -> signIn(account = result.data)
            }
        }
    }

    fun navigateTo(nav: ENavAuth) = with(RepositoryImpl) {
        job = CoroutineScope(Dispatchers.Main).launch {
            authNavMain.emit(nav)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}