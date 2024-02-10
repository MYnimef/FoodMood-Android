package com.mynimef.domain

import com.mynimef.domain.models.AccountModel
import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.ClientModel
import kotlinx.coroutines.flow.Flow

interface AppStorage {

    suspend fun getRefreshToken(): String

    suspend fun insertAccount(account: AccountModel)

    suspend fun deleteAccount(id: Long)

    suspend fun deleteAccount()

    fun getAllClients(): Flow<List<ClientModel>>

    fun getClient(): Flow<ClientModel?>

    suspend fun insertClient(client: ClientModel): Long

    suspend fun deleteClient(id: Long)

    suspend fun deleteClient()

    suspend fun updateWaterAmountClient(waterAmount: Float)

    suspend fun insertCard(card: CardModel): Long

    fun getAllCards(): Flow<List<CardModel>>

    suspend fun deleteAllCards()

}