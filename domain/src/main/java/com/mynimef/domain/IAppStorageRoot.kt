package com.mynimef.domain

import com.mynimef.domain.models.AccountModel

interface IAppStorageRoot {

    suspend fun insertAccount(account: AccountModel)

    suspend fun deleteAccount(accountId: Long)

}