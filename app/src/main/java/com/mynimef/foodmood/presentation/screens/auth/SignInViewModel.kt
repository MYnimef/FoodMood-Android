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
import com.mynimef.foodmood.domain.emailChecker
import com.mynimef.foodmood.domain.passwordChecker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {

    private var job: Job? = null

    private val _emailPair = MutableStateFlow("" to true)
    val emailPair = _emailPair.asStateFlow()

    private val _passwordPair = MutableStateFlow("" to true)
    val passwordPair = _passwordPair.asStateFlow()

    private val _buttonActive = MutableStateFlow(false)
    val buttonActive = _buttonActive.asStateFlow()

    fun setEmail(value: String) {
        _emailPair.value = value to emailChecker(value)
        checkButtonActive()
    }

    fun setPassword(value: String) {
        _passwordPair.value = value to passwordChecker(value)
        checkButtonActive()
    }

    private fun checkButtonActive() {
        _buttonActive.value = emailPair.value.second && passwordPair.value.second
    }

    fun signIn() = with(Repository) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val request = SignInRequest(
                email = _emailPair.value.first,
                password = _passwordPair.value.first,
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