package com.mynimef.domain

import com.mynimef.domain.models.AccountModel
import com.mynimef.domain.models.enums.EAppState
import com.mynimef.domain.models.enums.ERole
import com.mynimef.domain.models.requests.ISignInRequest
import com.mynimef.domain.models.requests.ISignUpRequest

class AppRepository(
    private val storage: IAppStorage,
    private val network: IAppNetwork
): IAppStorageRoot, IAppNetworkRoot {

    fun getAppState() = storage.getAppState()
    fun getActualAccountId() = storage.getActualAccountId()

    suspend fun signIn(account: AccountModel) {
        storage.insertAccount(account)

        setState(when (account.role) {
            ERole.CLIENT -> EAppState.CLIENT
            ERole.TRAINER -> EAppState.TRAINER
        })
    }

    suspend fun signOut(accountId: Long) {
        storage.deleteAccount(accountId)
        network.removeAccessToken()
        setState(EAppState.AUTH)
    }

    private suspend fun setState(state: EAppState) {
        storage.setAppState(state)
    }

    override suspend fun signUpClient(request: ISignUpRequest) = network.signUpClient(request)

    override suspend fun signIn(request: ISignInRequest) = network.signIn(request)

    override suspend fun insertAccount(account: AccountModel) = storage.insertAccount(account)

    override suspend fun deleteAccount(accountId: Long) = storage.deleteAccount(accountId)

}