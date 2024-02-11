package com.mynimef.domain

import com.mynimef.domain.models.AccountModel
import com.mynimef.domain.models.EAppState
import com.mynimef.domain.models.ERole
import kotlinx.coroutines.flow.last

class AppRepository(
    private val storageRoot: IAppStorageRoot,
    private val networkRoot: IAppNetworkRoot
) {

    val appState = storageRoot.appState

    val storage: IAppStorage = storageRoot
    val network: IAppNetwork = networkRoot

    suspend fun signIn(account: AccountModel) {
        storageRoot.insertAccount(account)

        setState(when (account.role) {
            ERole.CLIENT -> EAppState.CLIENT
            ERole.TRAINER -> EAppState.TRAINER
        })
    }

    suspend fun signOut() {
        storageRoot.deleteAllCards()
        storageRoot.deleteAccount()

        when (appState.last()) {
            EAppState.CLIENT -> storageRoot.deleteClient()
            EAppState.TRAINER -> {}
            else -> return
        }
        networkRoot.removeAccessToken()

        setState(EAppState.AUTH)
    }

    private suspend fun setState(state: EAppState) {
        storageRoot.setAppState(state)
    }

}