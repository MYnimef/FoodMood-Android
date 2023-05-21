package com.mynimef.foodmood.presentation.screens.shared

import android.os.Build
import androidx.lifecycle.ViewModel
import com.mynimef.foodmood.data.models.enums.ESignIn
import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel: ViewModel() {

    private var job: Job? = null

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _buttonActive = MutableStateFlow(false)
    val buttonActive: StateFlow<Boolean> = _buttonActive.asStateFlow()

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

    fun signIn() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val request = SignInRequest(
                email = _email.value,
                password = _password.value,
                device = Build.MANUFACTURER + " " + Build.MODEL,
            )
            val isSuccess = Repository.signIn(request)
            withContext(Dispatchers.Main) {
                when (isSuccess) {
                    ESignIn.SUCCESS -> {}
                    ESignIn.WRONG_PASSWORD -> {}
                    ESignIn.NO_CONNECTION -> {}
                    ESignIn.UNKNOWN -> {}
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}