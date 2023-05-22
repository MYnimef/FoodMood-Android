package com.mynimef.foodmood.presentation.screens.shared

import android.os.Build
import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.ESignUp
import com.mynimef.foodmood.data.repository.Repository
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel: ViewModel() {

    private var job: Job? = null

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _birthday = MutableStateFlow("")
    val birthday: StateFlow<String> = _name.asStateFlow()
    
    private val _firstButtonActive = MutableStateFlow(false)
    val firstButtonActive: StateFlow<Boolean> = _firstButtonActive.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _repeatPassword = MutableStateFlow("")
    val repeatPassword: StateFlow<String> = _repeatPassword.asStateFlow()

    private val _secondButtonActive = MutableStateFlow(false)
    val secondButtonActive: StateFlow<Boolean> = _secondButtonActive.asStateFlow()

    private var nameCheck = false
    private var emailCheck = false
    private var passwordCheck = false

    private val _food = MutableStateFlow(false)
    val food: StateFlow<Boolean> = _food.asStateFlow()

    private val _water = MutableStateFlow(false)
    val water: StateFlow<Boolean> = _water.asStateFlow()

    private val _weight = MutableStateFlow(false)
    val weight: StateFlow<Boolean> = _weight.asStateFlow()

    fun setName(value: String) {
        _name.value = value
        nameCheck = value.length >= 2
        checkFirstButtonActive()
    }

    private fun checkFirstButtonActive() {
        _firstButtonActive.value = nameCheck
    }

    fun setBirthday(value: String) {
        _birthday.value = value
    }

    fun setEmail(value: String) {
        _email.value = value
        emailCheck = value.isNotEmpty()
        checkSecondButtonActive()
    }
    fun setPassword(value: String) {
        _password.value = value
        passwordCheck = value.length >= 8 && value == _repeatPassword.value
        checkSecondButtonActive()
    }
    fun setRepeatPassword(value: String) {
        _repeatPassword.value = value
        passwordCheck = value.length >= 8 && value == _password.value
        checkSecondButtonActive()
    }

    private fun checkSecondButtonActive() {
        _secondButtonActive.value = emailCheck && passwordCheck
    }

    fun triggerFood() { _food.value = !_food.value }
    fun triggerWater() { _water.value = !_water.value }
    fun triggerWeight() { _weight.value = !_weight.value }

    fun signUp() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val request = SignUpRequest(
                name = _name.value,
                email = _email.value,
                password = _password.value,
                trackFood = _food.value,
                trackWater = _water.value,
                trackWeight = _weight.value,
                device = Build.MANUFACTURER + " " + Build.MODEL,
            )
            val response = Repository.signUp(request)
            withContext(Dispatchers.Main) {
                when (response) {
                    ESignUp.SUCCESS -> {}
                    ESignUp.WRONG_INPUT -> {}
                    ESignUp.ACCOUNT_EXISTS -> {}
                    ESignUp.NO_CONNECTION -> {}
                    ESignUp.UNKNOWN -> {}
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}