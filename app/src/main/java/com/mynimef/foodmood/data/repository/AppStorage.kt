package com.mynimef.foodmood.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mynimef.foodmood.data.models.database.AccountEntity
import com.mynimef.foodmood.data.models.database.CardEntity
import com.mynimef.foodmood.data.models.database.ClientEntity
import com.mynimef.foodmood.data.models.database.TrainerEntity
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.data.repository.dao.AccountDao
import com.mynimef.foodmood.data.repository.dao.CardDao
import com.mynimef.foodmood.data.repository.dao.ClientDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppStorage(context: Context) {

    private val sharedPref: SharedPreferences
    private val database: AppDatabase

    private var id: Long = 0

    private val _client by lazy { MutableStateFlow<ClientEntity?>(null) }
    val client by lazy { _client.asStateFlow() }

    private val _trainer by lazy { MutableStateFlow<TrainerEntity?>(null) }
    val trainer by lazy { _trainer.asStateFlow() }

    init {
        sharedPref = context.getSharedPreferences("food_mood", Context.MODE_PRIVATE)
        database =  Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "foodmood-database"
        )
            .fallbackToDestructiveMigration()
            .build()
        id = sharedPref.getLong("account_id", 0)
    }

    suspend fun initApp(): EAppState {
        val state = EAppState.fromInt(sharedPref.getInt("app_state", 0))
        when(state) {
            EAppState.AUTH -> {}
            EAppState.CLIENT -> _client.emit(database.clientDao().getClientById(id))
            EAppState.TRAINER -> {}
        }
        return state
    }

    fun setAppState(state: Int) = with(sharedPref.edit()) {
        putInt("app_state", state)
        apply()
    }

    fun setId(id: Long) = with (sharedPref.edit()) {
        putLong("account_id", id)
        apply()
    }

    suspend fun getRefreshToken() =
        database.accountDao().getRefreshTokenById(id)
    suspend fun insertAccount(account: AccountEntity) {
        id = database.accountDao().insert(account)
    }
    suspend fun deleteAccount(id: Long) =
        database.accountDao().deleteById(id)
    suspend fun deleteAccount() =
        database.accountDao().deleteById(id)

    suspend fun insertClient(client: ClientEntity) {
        database.clientDao().insert(client.copy(id = id))
        _client.value = client
    }
    suspend fun deleteClient(id: Long) =
        database.clientDao().deleteById(id)
    suspend fun deleteClient() =
        database.clientDao().deleteById(id)

    suspend fun insertCard(card: CardEntity) =
        database.cardDao().insert(card)
    fun getAllCards() =
        database.cardDao().getAll()
    suspend fun deleteAllCards() =
        database.cardDao().deleteAll()

    @Database(
        entities = [
            AccountEntity::class,
            ClientEntity::class,
            CardEntity::class,
        ],
        version = 1
    )
    abstract class AppDatabase: RoomDatabase() {
        abstract fun accountDao(): AccountDao
        abstract fun clientDao(): ClientDao
        abstract fun cardDao(): CardDao
    }
}