package com.mynimef.foodmood.data.repository

import android.content.Context
import com.mynimef.foodmood.data.models.database.AccountEntity
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.data.models.enums.ENavAuth
import com.mynimef.foodmood.data.models.enums.ENavClientBottomBar
import com.mynimef.foodmood.data.models.enums.ENavClientMain
import com.mynimef.foodmood.data.models.enums.ERole
import com.mynimef.foodmood.data.models.enums.EToast
import com.mynimef.foodmood.data.models.responses.SignInResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object Repository {

    lateinit var storage: AppStorage
    val network = AppNetwork()

    private val _appState = MutableStateFlow(EAppState.AUTH)
    val appState = _appState.asStateFlow()

    val authNavMain by lazy { MutableSharedFlow<ENavAuth>() }

    val clientNavMain by lazy { MutableSharedFlow<ENavClientMain>() }
    val clientNavBottomBar by lazy { MutableSharedFlow<ENavClientBottomBar>() }

    val toastFlow = MutableSharedFlow<EToast>()

    suspend fun init(context: Context) {
        storage = AppStorage(context)
        val state = storage.initApp()
        if (state != EAppState.AUTH) {
            network.refreshToken = storage.getRefreshToken()
        }
        _appState.value = state
    }

    private fun setState(state: EAppState) {
        storage.setAppState(state.value)
        _appState.value = state
    }

    suspend fun signIn(response: SignInResponse) {
        storage.insertAccount(
            AccountEntity(
                role = response.role,
                refreshToken = response.refreshToken,
            )
        )
        network.refreshToken = response.refreshToken
        network.accessToken = response.accessToken
        setState(when(response.role) {
            ERole.CLIENT -> EAppState.CLIENT
            ERole.TRAINER -> EAppState.TRAINER
        })
    }

    suspend fun signOut() {
        network.refreshToken = ""
        network.accessToken = null

        storage.deleteAllCards()
        storage.deleteAccount()
        when(appState.value) {
            EAppState.CLIENT -> storage.deleteClient()
            EAppState.TRAINER -> {}
            else -> return
        }

        setState(EAppState.AUTH)
    }

}