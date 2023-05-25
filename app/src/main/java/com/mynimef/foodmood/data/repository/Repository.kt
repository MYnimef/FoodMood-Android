package com.mynimef.foodmood.data.repository

import android.content.Context
import com.mynimef.foodmood.data.models.database.AccountEntity
import com.mynimef.foodmood.data.models.database.CardEntity
import com.mynimef.foodmood.data.models.database.ClientEntity
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.data.models.enums.ERole
import com.mynimef.foodmood.data.models.enums.EToast
import com.mynimef.foodmood.data.models.requests.ClientAddCardRequest
import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.models.responses.CardResponse
import com.mynimef.foodmood.data.models.responses.ClientResponse
import com.mynimef.foodmood.data.models.responses.SignInResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

object Repository {

    private lateinit var storage: AppLocalStorage
    private val network = AppNetwork()

    private var id: Long = 0

    val appState by lazy { storage.appState.asStateFlow() }
    val client by lazy { storage.client.asStateFlow() }
    val trainer by lazy { storage.trainer.asStateFlow() }

    private val toastFlow = MutableSharedFlow<EToast>()
    fun getToastFlow() = toastFlow.asSharedFlow()
    suspend fun toast(value: EToast) = toastFlow.emit(value)

    suspend fun init(context: Context) {
        storage = AppLocalStorage(context)

        val state = storage.getSavedState()
        if (state != EAppState.NONE) {
            id = storage.getSavedId()
            network.refreshToken = storage.database.getRefreshTokenById(id)

            if (state == EAppState.CLIENT) {
                storage.client.value = storage.database.getClient(id)
            }
        }
        storage.appState.value = state
    }

    suspend fun signUpClient(request: SignUpRequest) = network.signUpClient(request)
    suspend fun signIn(request: SignInRequest) = network.signIn(request)

    suspend fun getClient() = network.getClient()
    suspend fun clientAddCard(request: ClientAddCardRequest) = network.clientAddCard(request)
    suspend fun clientGetDayCards(day: Int, month: Int, year: Int) = network.clientGetDayCards(day, month, year)


    suspend fun addCardToStorage(card: CardResponse) {
        val cardEntity = CardEntity(
            mealType = card.mealType,
            emotionType = card.emotionType,
            emotionDescription = card.emotionDescription,
            foodDescription = card.foodDescription,
        )
        storage.database.insertCard(cardEntity)
    }
    fun getCardsFromStorage() = storage.database.getAllCards()

    suspend fun signIn(response: SignInResponse) {
        val id = storage.database.insertAccount(
            AccountEntity(
                role = response.role,
                refreshToken = response.refreshToken,
            )
        )
        storage.setSavedId(id)
        storage.setState(when(response.role) {
            ERole.CLIENT -> EAppState.CLIENT
            ERole.TRAINER -> EAppState.TRAINER
        })
        network.refreshToken = response.refreshToken
        network.accessToken = response.accessToken
    }

    suspend fun signOut() {
        val state = appState.value
        storage.setState(EAppState.NONE)

        network.refreshToken = ""
        network.accessToken = null

        storage.database.deleteAllCards()
        storage.database.deleteAccount(id)
        when(state) {
            EAppState.CLIENT -> storage.database.deleteClient(id)
            EAppState.TRAINER -> {}
            else -> return
        }

        id = 0
        storage.setSavedId(id)
    }

    suspend fun setClient(client: ClientResponse) {
        val clientEntity = ClientEntity(
            id = id,
            name = client.name,
            trackFood = client.trackFood,
            trackWater = client.trackWater,
            trackWeight = client.trackWeight,
        )
        storage.database.insertClient(clientEntity)
        storage.client.value = clientEntity
    }

}