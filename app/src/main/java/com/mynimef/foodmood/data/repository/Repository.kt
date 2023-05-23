package com.mynimef.foodmood.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.mynimef.foodmood.data.models.database.ClientEntity
import com.mynimef.foodmood.data.models.database.AccountEntity
import com.mynimef.foodmood.data.models.database.TrainerEntity
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.data.models.enums.ECallback
import com.mynimef.foodmood.data.models.requests.ClientAddCardRequest
import com.mynimef.foodmood.data.models.requests.RefreshTokenRequest
import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.models.responses.SignInResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException

object Repository {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var database: AppDatabase
    private lateinit var network: NetworkService

    private val _appState = MutableStateFlow(EAppState.NONE)
    val appState = _appState.asStateFlow()

    private lateinit var refreshToken: String
    private var accessToken: String? = null

    private val _client = MutableSharedFlow<ClientEntity>(replay = 1)
    val client: SharedFlow<ClientEntity> = _client

    private val _trainer = MutableSharedFlow<TrainerEntity>(replay = 1)
    val trainer: SharedFlow<TrainerEntity> = _trainer

    private fun setState(state: EAppState) {
        _appState.value = state
        with (sharedPref.edit()) {
            putInt("app_state", state.value)
            apply()
        }
    }

    suspend fun init(context: Context) {
        sharedPref = context.getSharedPreferences("food_mood", Context.MODE_PRIVATE)
        database = AppDatabase.init(context)
        network = NetworkService()

        val state = EAppState.fromInt(sharedPref.getInt("app_state", 0))
        _appState.value = state
        if (state != EAppState.NONE) {
            val id = sharedPref.getLong("account_id", 0)
            refreshToken = database.getRefreshTokenById(id)
            refreshAccessToken()
        }
    }

    suspend fun signUp(request: SignUpRequest): ECallback {
        return try {
            val response = network.signUpClient(request)
            if (response.isSuccessful) {
                signIn(response.body()!!)
                ECallback.SUCCESS
            } else {
                when (response.code()) {
                    403 -> ECallback.WRONG_INPUT
                    409 -> ECallback.DATA_CONFLICT
                    else -> ECallback.UNKNOWN
                }
            }
        } catch (e: IOException) {
            ECallback.NO_CONNECTION
        }
    }

    suspend fun signIn(request: SignInRequest): ECallback {
        return try {
            val response = network.signIn(request)
            if (response.isSuccessful) {
                signIn(response.body()!!)
                ECallback.SUCCESS
            } else {
                when (response.code()) {
                    401 -> ECallback.UNAUTHORIZED
                    else -> ECallback.UNKNOWN
                }
            }
        } catch (e: IOException) {
            ECallback.NO_CONNECTION
        }
    }

    private suspend fun signIn(response: SignInResponse) {
        val id = database.insertAccount(AccountEntity(
            refreshToken = response.refreshToken,
        ))
        with (sharedPref.edit()) {
            putLong("account_id", id)
            apply()
        }
        setState(when (response.role) {
            SignInResponse.Role.CLIENT -> EAppState.CLIENT
            SignInResponse.Role.TRAINER -> EAppState.TRAINER
        })
        refreshToken = response.refreshToken
        accessToken = response.accessToken
    }

    private suspend fun refreshAccessToken(): ECallback {
        accessToken = null
        val request = RefreshTokenRequest(
            refreshToken = refreshToken
        )
        return try {
            val response = network.refreshToken(request)
            if (response.isSuccessful) {
                accessToken = response.body()!!.accessToken
                ECallback.SUCCESS
            } else {
                setState(EAppState.NONE)
                ECallback.UNAUTHORIZED
            }
        } catch (e: IOException) {
            ECallback.NO_CONNECTION
        }
    }

    private suspend fun <T> refreshAccessToken(
        request: T,
        handler: suspend (T) -> ECallback,
    ): ECallback {
        val response = refreshAccessToken()
        return if (response == ECallback.SUCCESS) {
            handler(request)
        } else {
            response
        }
    }

    suspend fun clientAddCard(request: ClientAddCardRequest): ECallback {
        if (accessToken == null) {
            return refreshAccessToken(request = request, handler = ::clientAddCard)
        }
        return try {
            val response = network.clientAddCard(accessToken!!, request)
            if (response.isSuccessful) {
                ECallback.SUCCESS
            } else {
                when(response.code()) {
                    401 -> refreshAccessToken(request = request, handler = ::clientAddCard)
                    403 -> ECallback.WRONG_INPUT
                    409 -> ECallback.DATA_CONFLICT
                    else -> ECallback.UNKNOWN
                }
            }
        } catch (e: IOException) {
            ECallback.NO_CONNECTION
        }
    }

}