package com.mynimef.foodmood.presentation.screens.auth

import android.os.Build
import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.ApiError
import com.mynimef.foodmood.data.models.ApiException
import com.mynimef.foodmood.data.models.ApiSuccess
import com.mynimef.foodmood.data.models.enums.ENavAuth
import com.mynimef.foodmood.data.models.enums.EToast
import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {

    private var job: Job? = null

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _buttonActive = MutableStateFlow(false)
    val buttonActive = _buttonActive.asStateFlow()

    private var emailCheck = false
    private var passwordCheck = false

    fun setEmail(value: String) {
        _email.value = value
        emailCheck = value.isNotEmpty()
        checkButtonActive()
    }
    fun setPassword(value: String) {
        _password.value = value
        passwordCheck = value.length >= 8
        checkButtonActive()
    }

    private fun checkButtonActive() {
        _buttonActive.value = emailCheck && passwordCheck
    }

    fun signIn() = with(Repository) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val request = SignInRequest(
                email = _email.value,
                password = _password.value,
                device = Build.MANUFACTURER + " " + Build.MODEL,
            )
            when (val result = network.signIn(request)) {
                is ApiError -> {
                    when (result.code) {
                        401 -> toastFlow.emit(EToast.WRONG_EMAIL_OR_PASSWORD)
                        403 -> toastFlow.emit(EToast.WRONG_INPUT)
                        else -> {}
                    }
                }
                is ApiException -> toastFlow.emit(EToast.NO_CONNECTION)
                is ApiSuccess -> signIn(result.data)
            }
        }
    }

    fun signUp() = with(Repository) {
        job = CoroutineScope(Dispatchers.Main).launch {
            authNavMain.emit(ENavAuth.SIGN_UP)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}