package com.mynimef.domain

import com.mynimef.domain.models.AccountModel
import com.mynimef.domain.models.EAppState
import com.mynimef.domain.models.ERole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class ARepository {

    protected abstract val storage: IAppStorageRoot

    protected abstract val networkRoot: IAppNetworkRoot

    private val _appState = MutableStateFlow(EAppState.AUTH)
    val appState = _appState.asStateFlow()

    suspend fun signIn(account: AccountModel, accessToken: String) {
        storage.insertAccount(account)

        networkRoot.updateRefreshToken(account.refreshToken)
        networkRoot.updateAccessToken(accessToken)

        setState(when(account.role) {
            ERole.CLIENT -> EAppState.CLIENT
            ERole.TRAINER -> EAppState.TRAINER
        })
    }

    suspend fun signOut() {
        networkRoot.updateRefreshToken("")
        networkRoot.updateAccessToken(null)

        storage.deleteAllCards()
        storage.deleteAccount()

        when(appState.value) {
            EAppState.CLIENT -> storage.deleteClient()
            EAppState.TRAINER -> {}
            else -> return
        }

        setState(EAppState.AUTH)
    }

    private fun setState(state: EAppState) {
        storage.setAppState(state)
        _appState.value = state
    }

}