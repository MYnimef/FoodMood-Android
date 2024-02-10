package com.mynimef.data

import android.content.Context
import com.mynimef.data.enums.ENavAuth
import com.mynimef.data.enums.ENavClientBottomBar
import com.mynimef.data.enums.ENavClientMain
import com.mynimef.data.enums.EToast
import com.mynimef.domain.AppNetwork
import com.mynimef.domain.models.AccountModel
import com.mynimef.domain.models.EAppState
import com.mynimef.domain.models.ERole
import com.mynimef.data_local.AppStorageImpl
import com.mynimef.data_remote.AppNetworkImpl
import com.mynimef.domain.AppStorage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object RepositoryImpl {

    lateinit var storage: AppStorage
    lateinit var _storage: AppStorageImpl
    val network: AppNetwork = AppNetworkImpl()

    private val _appState = MutableStateFlow(EAppState.AUTH)
    val appState = _appState.asStateFlow()

    val authNavMain by lazy { MutableSharedFlow<ENavAuth>() }

    val clientNavMain by lazy { MutableSharedFlow<ENavClientMain>() }
    val clientNavBottomBar by lazy { MutableSharedFlow<ENavClientBottomBar>() }

    val toastFlow = MutableSharedFlow<EToast>()

    suspend fun init(context: Context) {
        _storage = AppStorageImpl(context)
        storage = _storage
        val state = _storage.initApp()
        if (state != EAppState.AUTH) {
            network.updateRefreshToken(storage.getRefreshToken())
        }
        _appState.value = state
    }

    private fun setState(state: EAppState) {
        _storage.setAppState(state.value)
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