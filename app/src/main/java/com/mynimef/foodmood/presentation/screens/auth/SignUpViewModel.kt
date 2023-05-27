package com.mynimef.foodmood.presentation.screens.auth


import android.os.Build
import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.ApiError
import com.mynimef.foodmood.data.models.ApiException
import com.mynimef.foodmood.data.models.ApiSuccess
import com.mynimef.foodmood.data.models.enums.EToast
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _repeatPassword = MutableStateFlow("")
    val repeatPassword = _repeatPassword.asStateFlow()

    private val _secondButtonActive = MutableStateFlow(false)
    val secondButtonActive = _secondButtonActive.asStateFlow()

    private var emailCheck = false
    private var passwordCheck = false

    fun setName(value: String) {
        _name.value = value
        nameCheck = value.length >= 2
        checkFirstButtonActive()
    }
    fun setBirthday(value: String) {
        _birthday.value = value
        birthdayCheck = value.isNotEmpty() && (value != LocalDate.now().toString())
        checkFirstButtonActive()
    }
    private fun checkFirstButtonActive() {
        _firstButtonActive.value = nameCheck && birthdayCheck
    }

    fun triggerFood() { _food.value = !_food.value }
    fun triggerWater() { _water.value = !_water.value }
    fun triggerWeight() { _weight.value = !_weight.value }

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
            when (val result = Repository.signUpClient(request)) {
                is ApiError -> {
                    when (result.code) {
                        403 -> Repository.toast(EToast.WRONG_INPUT)
                        409 -> Repository.toast(EToast.ACCOUNT_ALREADY_EXISTS)
                        else -> {}
                    }
                }
                is ApiException -> Repository.toast(EToast.NO_CONNECTION)
                is ApiSuccess -> Repository.signIn(result.data)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}