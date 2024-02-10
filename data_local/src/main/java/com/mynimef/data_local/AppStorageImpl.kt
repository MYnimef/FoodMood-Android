package com.mynimef.data_local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mynimef.domain.AppStorage
import com.mynimef.domain.models.AccountModel
import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.ClientModel
import com.mynimef.domain.models.EAppState
import com.mynimef.data_local.dao.AccountDao
import com.mynimef.data_local.dao.CardDao
import com.mynimef.data_local.dao.ClientDao
import com.mynimef.data_local.models.AccountEntity
import com.mynimef.data_local.models.CardEntity
import com.mynimef.data_local.models.ClientEntity
import com.mynimef.data_local.models.TrainerEntity
import kotlinx.coroutines.flow.Flow

class AppStorageImpl(context: Context): AppStorage {

    private val sharedPref = context.getSharedPreferences("food_mood", Context.MODE_PRIVATE)
    private val database = Room.databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = "foodmood-database"
    )
        .fallbackToDestructiveMigration()
        .build()

    private var id = sharedPref.getLong("account_id", 0)

    suspend fun initApp(): EAppState {
        val state = EAppState.fromInt(sharedPref.getInt("app_state", 0))
        return state
    }

    fun setAppState(state: Int) = with(sharedPref.edit()) {
        putInt("app_state", state)
        apply()
    }

    fun setId(id: Long) {
        with (sharedPref.edit()) {
            putLong("account_id", id)
            apply()
        }
        this.id = id
    }

    override suspend fun getRefreshToken() =
        database.accountDao().getRefreshTokenById(id)
    override suspend fun insertAccount(account: AccountModel) =
        setId(database.accountDao().insert(AccountEntity.fromModel(account)))
    override suspend fun deleteAccount(id: Long) =
        database.accountDao().deleteById(id)
    override suspend fun deleteAccount() =
        database.accountDao().deleteById(id)

    override fun getAllClients(): Flow<List<ClientModel>> =
        database.clientDao().getAll()
    override fun getClient(): Flow<ClientModel?> =
        database.clientDao().getClientById(id)
    override suspend fun insertClient(client: ClientModel) =
        database.clientDao().insert(ClientEntity.fromModel(client, id))
    override suspend fun deleteClient(id: Long) =
        database.clientDao().deleteById(id)
    override suspend fun deleteClient() =
        database.clientDao().deleteById(id)
    override suspend fun updateWaterAmountClient(waterAmount: Float) =
        database.clientDao().updateWaterAmount(id = id, waterAmount = waterAmount)

    override suspend fun insertCard(card: CardModel) =
        database.cardDao().insert(CardEntity.fromModel(card))
    override fun getAllCards(): Flow<List<CardModel>> =
        database.cardDao().getAll()
    override suspend fun deleteAllCards() =
        database.cardDao().deleteAll()

    @Database(
        entities = [
            AccountEntity::class,
            ClientEntity::class,
            TrainerEntity::class,
            CardEntity::class,
        ],
        version = 1,
        exportSchema = false
    )
    internal abstract class AppDatabase: RoomDatabase() {
        abstract fun accountDao(): AccountDao
        abstract fun clientDao(): ClientDao
        abstract fun cardDao(): CardDao
    }

}