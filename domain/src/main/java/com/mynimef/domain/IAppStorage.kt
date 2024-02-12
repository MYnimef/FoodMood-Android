package com.mynimef.domain

import com.mynimef.domain.models.AccountModel
import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.ClientModel
import kotlinx.coroutines.flow.Flow

interface IAppStorage {

    suspend fun insertAccount(account: AccountModel)

    suspend fun deleteAccount(accountId: Long)

    fun getAllClients(): Flow<List<ClientModel>>

    suspend fun getClient(accountId: Long): ClientModel?

    suspend fun insertClient(accountId: Long, client: ClientModel): Long

    suspend fun deleteClient(accountId: Long)

    suspend fun updateWaterAmountClient(accountId: Long, waterAmount: Float)

    suspend fun insertCard(card: CardModel): Long

    fun getAllCards(): Flow<List<CardModel>>

    suspend fun deleteAllCards()

}