package com.mynimef.foodmood.presentation.screens.shared

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignupViewModel: ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _login = MutableStateFlow("")
    val login: StateFlow<String> = _login.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _repeatPassword = MutableStateFlow("")
    val repeatPassword: StateFlow<String> = _repeatPassword.asStateFlow()

    fun setName(value: String) {
        _name.value = value
    }

    fun setLogin(value: String) {
        _login.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun setRepeatPassword(value: String) {
        _repeatPassword.value = value
    }
}