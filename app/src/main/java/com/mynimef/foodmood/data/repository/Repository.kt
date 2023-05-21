package com.mynimef.foodmood.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.mynimef.foodmood.data.models.database.AccountEntity
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.models.responses.SignInResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object Repository {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var database: AppDatabase
    private lateinit var network: NetworkService

    private var accountID = 1
    private var accessToken: String? = null

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
        }
    }

    suspend fun signUp(request: SignUpRequest): Boolean {
        val response = network.signUp(request)
        if (response.isSuccessful) {
            signIn(response.body()!!)
            return true
        }
        return false
    }

    suspend fun signIn(request: SignInRequest): Boolean {
        val response = network.signIn(request)
        if (response.isSuccessful) {
            signIn(response.body()!!)
            return true
        }
        return false
    }

    private suspend fun signIn(response: SignInResponse) {
        database.accountDao().insert(AccountEntity(
            name = response.name,
            refreshToken = response.refreshToken,
        ))
        setState(when (response.role) {
            SignInResponse.Role.USER -> EAppState.CLIENT
            SignInResponse.Role.TRAINER -> EAppState.TRAINER
        })
        accessToken = response.accessToken
    }
}