package com.mynimef.domain

import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.ClientModel
import com.mynimef.domain.models.enums.ENavClientBottomBar
import com.mynimef.domain.models.enums.ENavClientMain
import com.mynimef.domain.models.requests.IClientAddCardRequest
import com.mynimef.domain.models.requests.IClientDataRequest
import com.mynimef.domain.models.requests.IClientInfoRequest
import com.mynimef.domain.models.requests.IWaterIncreaseRequest
import kotlinx.coroutines.flow.MutableSharedFlow

class ClientRepository(
    private val storage: IClientStorage,
    private val network: IClientNetwork,
    private val appRepository: AppRepository
) : IClientStorage, IClientNetwork {

    val navMain by lazy { MutableSharedFlow<ENavClientMain>() }
    val navBottomBar by lazy { MutableSharedFlow<ENavClientBottomBar>() }

    suspend fun signOut(accountId: Long) {
        storage.deleteAllCards()
        storage.deleteAccount(accountId)
        appRepository.signOut(accountId)
    }

    override suspend fun getInfo(
        accountId: Long,
        request: IClientInfoRequest
    ) = network.getInfo(accountId, request)

    override suspend fun addCard(
        accountId: Long,
        request: IClientAddCardRequest
    ) = network.addCard(accountId, request)

    override suspend fun getDayCards(
        accountId: Long,
        day: Int,
        month: Int,
        year: Int
    ) = network.getDayCards(accountId, day, month, year)

    override suspend fun increaseWater(
        accountId: Long,
        request: IWaterIncreaseRequest
    ) = network.increaseWater(accountId, request)

    override suspend fun getData(
        accountId: Long,
        request: IClientDataRequest
    ) = network.getData(accountId, request)

    override fun getAllAccounts() = storage.getAllAccounts()

    override suspend fun getAccount(accountId: Long) = storage.getAccount(accountId)

    override suspend fun insertAccount(accountId: Long, client: ClientModel) = storage.insertAccount(accountId, client)

    override suspend fun deleteAccount(accountId: Long) = storage.deleteAccount(accountId)

    override suspend fun updateWaterAmount(accountId: Long, waterAmount: Float) = storage.updateWaterAmount(accountId, waterAmount)

    override suspend fun insertCard(card: CardModel) = storage.insertCard(card)

    override fun getAllCards() = storage.getAllCards()

    override suspend fun deleteAllCards() = storage.deleteAllCards()

}