package com.mynimef.foodmood.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.mynimef.foodmood.data.models.database.AccountEntity
import com.mynimef.foodmood.data.models.enums.AppState
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.models.responses.SignInResponse

object Repository {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var database: AppDatabase
    private lateinit var network: NetworkService

    private lateinit var accessToken: String

    var appState: AppState = AppState.NONE
        private set

    fun init(context: Context) {
        sharedPref = context.getSharedPreferences("food_mood", Context.MODE_PRIVATE)
        database = AppDatabase.init(context)
        network = NetworkService()

        appState = AppState.fromInt(sharedPref.getInt("app_state", 0))
        if (appState != AppState.NONE) {
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
                appState = when (it.role) {
                    SignInResponse.Role.USER -> AppState.USER
                    SignInResponse.Role.TRAINER -> AppState.TRAINER
                }
                accessToken = it.accessToken
            }
            return true
        }
        return false
    }

    fun signIn() {

    }
}