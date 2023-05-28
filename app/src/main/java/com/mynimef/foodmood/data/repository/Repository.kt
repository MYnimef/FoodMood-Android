package com.mynimef.foodmood.data.repository

import android.content.Context
import com.mynimef.foodmood.data.models.database.AccountEntity
import com.mynimef.foodmood.data.models.database.CardEntity
import com.mynimef.foodmood.data.models.database.ClientEntity
import com.mynimef.foodmood.data.models.database.TrainerEntity
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.data.models.enums.ENavAuth
import com.mynimef.foodmood.data.models.enums.ENavClientBottomBar
import com.mynimef.foodmood.data.models.enums.ENavClientMain
import com.mynimef.foodmood.data.models.enums.ERole
import com.mynimef.foodmood.data.models.enums.EToast
import com.mynimef.foodmood.data.models.requests.ClientAddCardRequest
import com.mynimef.foodmood.data.models.requests.ClientInfoRequest
import com.mynimef.foodmood.data.models.requests.SignInRequest
import com.mynimef.foodmood.data.models.requests.SignUpRequest
import com.mynimef.foodmood.data.models.requests.WaterIncreaseRequest
import com.mynimef.foodmood.data.models.responses.CardResponse
import com.mynimef.foodmood.data.models.responses.ClientInfoResponse
import com.mynimef.foodmood.data.models.responses.SignInResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

object Repository {

    private lateinit var storage: AppStorage
    private val network = AppNetwork()

    private var id: Long = 0

    private val _appState = MutableStateFlow(EAppState.AUTH)
    val appState = _appState.asStateFlow()

    val authNavMain by lazy { MutableSharedFlow<ENavAuth>() }

    private val _client by lazy { MutableStateFlow(ClientEntity(id = 0, name = "", trackFood = true, trackWater = true, trackWeight = true, waterAmount = 0f)) }
    val client by lazy { _client.asStateFlow() }
    val clientNavMain by lazy { MutableSharedFlow<ENavClientMain>() }
    val clientNavBottomBar by lazy { MutableSharedFlow<ENavClientBottomBar>(replay = 1) }

    private val _trainer by lazy { MutableStateFlow(TrainerEntity(id = 0)) }
    val trainer by lazy { _trainer.asStateFlow() }

    private val toastFlow = MutableSharedFlow<EToast>()
    fun getToastFlow() = toastFlow.asSharedFlow()
    suspend fun toast(value: EToast) = toastFlow.emit(value)

    suspend fun init(context: Context) {
        storage = AppStorage(context)

        val state = storage.getAppState()
        if (state != EAppState.AUTH) {
            id = storage.getId()
            network.refreshToken = storage.database.getRefreshTokenById(id)

            if (state == EAppState.CLIENT) {
                _client.value = storage.database.getClient(id)
            }
        }
        _appState.value = state
    }

    private fun setState(state: EAppState) {
        storage.setAppState(state.value)
        _appState.value = state
    }

    suspend fun signUpClient(request: SignUpRequest) = network.signUpClient(request)
    suspend fun signIn(request: SignInRequest) = network.signIn(request)

    suspend fun clientGetInfo(request: ClientInfoRequest) = network.clientGetInfo(request)
    suspend fun clientAddCard(request: ClientAddCardRequest) = network.clientAddCard(request)
    suspend fun clientGetDayCards(day: Int, month: Int, year: Int) = network.clientGetDayCards(day, month, year)
    suspend fun clientIncreaseWater(request: WaterIncreaseRequest) = network.clientIncreaseWater(request)

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
        storage.setId(id)
        network.refreshToken = response.refreshToken
        network.accessToken = response.accessToken
        setState(when(response.role) {
            ERole.CLIENT -> EAppState.CLIENT
            ERole.TRAINER -> EAppState.TRAINER
        })
    }

    suspend fun signOut() {
        network.refreshToken = ""
        network.accessToken = null

        storage.database.deleteAllCards()
        storage.database.deleteAccount(id)
        when(appState.value) {
            EAppState.CLIENT -> storage.database.deleteClient(id)
            EAppState.TRAINER -> {}
            else -> return
        }

        id = 0
        storage.setId(id)

        setState(EAppState.AUTH)
    }

    suspend fun initClient(client: ClientInfoResponse) {
        storage.database.deleteAllCards()
        client.dayCards.forEach {
            addCardToStorage(it)
        }
        val clientEntity = ClientEntity(
            id = id,
            name = client.name,
            trackFood = client.trackFood,
            trackWater = client.trackWater,
            trackWeight = client.trackWeight,
            waterAmount = client.waterAmount,
        )
        storage.database.insertClient(clientEntity)
        _client.value = clientEntity
    }

}