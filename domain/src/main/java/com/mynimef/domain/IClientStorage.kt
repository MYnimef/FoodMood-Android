package com.mynimef.domain

import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.ClientModel
import kotlinx.coroutines.flow.Flow

interface IClientStorage {

    fun getAllAccounts(): Flow<List<ClientModel>>

    suspend fun getAccount(accountId: Long): ClientModel?

    suspend fun insertAccount(accountId: Long, client: ClientModel): Long

    suspend fun deleteAccount(accountId: Long)

    suspend fun updateWaterAmount(accountId: Long, waterAmount: Float)

    suspend fun insertCard(card: CardModel): Long

    fun getAllCards(): Flow<List<CardModel>>

    suspend fun deleteAllCards()

}