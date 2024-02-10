package com.mynimef.foodmood.data.repository

import android.content.Context
import com.mynimef.domain.AppNetwork
import com.mynimef.foodmood.data.models.enums.ENavAuth
import com.mynimef.foodmood.data.models.enums.ENavClientBottomBar
import com.mynimef.foodmood.data.models.enums.ENavClientMain
import com.mynimef.foodmood.data.models.enums.EToast
import com.mynimef.domain.models.AccountModel
import com.mynimef.domain.models.EAppState
import com.mynimef.domain.models.ERole
import com.mynimef.local_data.AppStorageImpl
import com.mynimef.remote_data.AppNetworkImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object RepositoryImpl {

    lateinit var storage: AppStorageImpl
    val network: AppNetwork = AppNetworkImpl()

    private val _appState = MutableStateFlow(EAppState.AUTH)
    val appState = _appState.asStateFlow()

    val authNavMain by lazy { MutableSharedFlow<ENavAuth>() }

    val clientNavMain by lazy { MutableSharedFlow<ENavClientMain>() }
    val clientNavBottomBar by lazy { MutableSharedFlow<ENavClientBottomBar>() }

    val toastFlow = MutableSharedFlow<EToast>()

    suspend fun init(context: Context) {
        storage = AppStorageImpl(context)
        val state = storage.initApp()
        if (state != EAppState.AUTH) {
            network.updateRefreshToken(storage.getRefreshToken())
        }
        _appState.value = state
    }

    private fun setState(state: EAppState) {
        storage.setAppState(state.value)
        _appState.value = state
    }

    suspend fun signIn(account: AccountModel, accessToken: String) {
        storage.insertAccount(account)
        network.updateRefreshToken(account.refreshToken)
        network.updateAccessToken(accessToken)
        setState(when(account.role) {
            ERole.CLIENT -> EAppState.CLIENT
            ERole.TRAINER -> EAppState.TRAINER
        })
    }

    suspend fun signOut() {
        network.updateRefreshToken("")
        network.updateAccessToken(null)

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