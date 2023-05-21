package com.mynimef.foodmood.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.mynimef.foodmood.data.models.database.AccountEntity
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.models.responses.SignInResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object Repository {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var database: AppDatabase
    private lateinit var network: NetworkService

    private var accountID = 1
    private lateinit var accessToken: String

    private val _appState = MutableStateFlow(EAppState.NONE)
    val appState = _appState.asStateFlow()

    private fun setState(state: EAppState) {
        _appState.value = state
        with (sharedPref.edit()) {
            putInt("app_state", state.value)
            apply()
        }
    }

    fun init(context: Context) {
        sharedPref = context.getSharedPreferences("food_mood", Context.MODE_PRIVATE)
        database = AppDatabase.init(context)
        network = NetworkService()

        val state = EAppState.fromInt(sharedPref.getInt("app_state", 0))
        _appState.value = state
        if (state != EAppState.NONE) {
            accountID = sharedPref.getInt("account_id", 1)
            accessToken = ""
        }
    }

    suspend fun signUp(request: SignUpRequest): Boolean {
        val response = network.signUp(request)
        if (response.isSuccessful) {
            response.body()!!.let {
                database.accountDao().insert(AccountEntity(
                    name = it.name,
                    refreshToken = it.refreshToken,
                ))
                setState(when (it.role) {
                    SignInResponse.Role.USER -> EAppState.CLIENT
                    SignInResponse.Role.TRAINER -> EAppState.TRAINER
                })
                accessToken = it.accessToken
            }
            return true
        }
        return false
    }

    fun signIn() {

    }
}