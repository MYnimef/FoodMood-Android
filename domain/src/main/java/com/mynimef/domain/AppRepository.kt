package com.mynimef.domain

import com.mynimef.domain.models.AccountModel
import com.mynimef.domain.models.EAppState
import com.mynimef.domain.models.ERole

class AppRepository(
    private val storageRoot: IAppStorageRoot,
    private val networkRoot: IAppNetworkRoot
) {

    fun getAppState() = storageRoot.getAppState()
    fun getActualAccountId() = storageRoot.getActualAccountId()

    val storage: IAppStorage = storageRoot
    val network: IAppNetwork = networkRoot

    suspend fun signIn(account: AccountModel) {
        storageRoot.insertAccount(account)

        setState(when (account.role) {
            ERole.CLIENT -> EAppState.CLIENT
            ERole.TRAINER -> EAppState.TRAINER
        })
    }

    suspend fun signOutClient(accountId: Long) {
        storageRoot.deleteAllCards()
        storageRoot.deleteAccount(accountId)

        storageRoot.deleteClient(accountId)
        networkRoot.removeAccessToken()

        setState(EAppState.AUTH)
    }

    suspend fun signOutTrainer(accountId: Long) {
        storageRoot.deleteAllCards()
        storageRoot.deleteAccount(accountId)

        networkRoot.removeAccessToken()

        setState(EAppState.AUTH)
    }

    private suspend fun setState(state: EAppState) {
        storageRoot.setAppState(state)
    }

}