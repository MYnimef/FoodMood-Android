package com.mynimef.foodmood.screens.auth

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynimef.data.enums.EMessage
import com.mynimef.domain.ApiError
import com.mynimef.domain.ApiException
import com.mynimef.domain.ApiSuccess
import com.mynimef.domain.AppRepository
import com.mynimef.domain.AuthRepository
import com.mynimef.domain.extensions.emailChecker
import com.mynimef.domain.extensions.passwordChecker
import com.mynimef.domain.models.enums.ENavAuth
import com.mynimef.domain.models.requests.ISignInRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AppRepository,
    private val authRepository: AuthRepository
): ViewModel() {

    private val _messagesFlow = MutableSharedFlow<EMessage>()
    val messagesFlow = _messagesFlow.asSharedFlow()

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

    fun signIn() {
        viewModelScope.launch(Dispatchers.IO) {
            val request = ISignInRequest.create(
                email = _emailPair.value.first,
                password = _passwordPair.value.first,
                device = Build.MANUFACTURER + " " + Build.MODEL,
            )
            when (val result = repository.signIn(request)) {
                is ApiError -> {
                    when (result.code) {
                        401 -> {}
                        403 -> {}
                        else -> {}
                    }
                }
                is ApiException -> _messagesFlow.emit(EMessage.NO_CONNECTION)
                is ApiSuccess -> repository.signIn(account = result.data)
            }
        }
    }

    fun signUp() {
        viewModelScope.launch(Dispatchers.Main) {
            authRepository.navMain.emit(ENavAuth.SIGN_UP)
        }
    }

}