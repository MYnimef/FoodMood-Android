package com.mynimef.foodmood.screens.auth

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.extensions.emailChecker
import com.mynimef.domain.extensions.passwordChecker
import com.mynimef.domain.models.requests.ISignInRequest
import com.mynimef.data.enums.ENavAuth
import com.mynimef.data.enums.EToast
import com.mynimef.data.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {

    private var job: Job? = null

    private val _emailPair = MutableStateFlow("" to true)
    val emailPair = _emailPair.asStateFlow()

    private val _passwordPair = MutableStateFlow("" to true)
    val passwordPair = _passwordPair.asStateFlow()

    val buttonActive = combine(
        emailPair,
        passwordPair
    ) { f1, f2 ->
        f1.second && f2.second && f1.first.isNotEmpty() && f2.first.isNotEmpty()
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )

    fun setEmail(value: String) {
        _emailPair.value = value to emailChecker(value)
    }

    fun setPassword(value: String) {
        _passwordPair.value = value to passwordChecker(value)
    }

    fun signIn() = with(RepositoryImpl) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val request = ISignInRequest.create(
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
                is ApiSuccess -> signIn(
                    account = result.data,
                    accessToken = result.data.accessToken
                )
            }
        }
    }

    fun signUp() = with(RepositoryImpl) {
        job = CoroutineScope(Dispatchers.Main).launch {
            authNavMain.emit(ENavAuth.SIGN_UP)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}