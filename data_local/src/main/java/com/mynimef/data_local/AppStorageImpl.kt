package com.mynimef.data_local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mynimef.data_local.dao.AccountDao
import com.mynimef.data_local.dao.CardDao
import com.mynimef.data_local.dao.ClientDao
import com.mynimef.data_local.models.AccountEntity
import com.mynimef.data_local.models.CardEntity
import com.mynimef.data_local.models.ClientEntity
import com.mynimef.data_local.models.TrainerEntity
import com.mynimef.domain.IAppStorageRoot
import com.mynimef.domain.models.AccountModel
import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.ClientModel
import com.mynimef.domain.models.EAppState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppStorageImpl(context: Context): IAppStorageRoot {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "food_mood")
    private val dataStore = context.dataStore

    private val PREFERENCE_APP_STATE = intPreferencesKey("app_state")
    override fun getAppState() = dataStore.data
        .map { preferences ->
            EAppState.fromInt(preferences[PREFERENCE_APP_STATE] ?: EAppState.AUTH.value)
        }

    override suspend fun setAppState(state: EAppState) {
        dataStore.edit { settings ->
            settings[PREFERENCE_APP_STATE] = state.value
        }
    }

    private val PREFERENCE_ID = longPreferencesKey("account_id")
    override fun getActualAccountId() = dataStore.data
        .map { preferences ->
            preferences[PREFERENCE_ID] ?: -1L
        }

    private suspend fun setId(id: Long) {
        dataStore.edit { settings ->
            settings[PREFERENCE_ID] = id
        }
    }

    private val database = Room.databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = "foodmood-database"
    )
        .fallbackToDestructiveMigration()
        .build()

    override suspend fun getRefreshToken(accountId: Long) =
        database.accountDao().getRefreshTokenById(id = accountId)
    override suspend fun insertAccount(account: AccountModel) =
        setId(database.accountDao().insert(AccountEntity.fromModel(account)))
    override suspend fun deleteAccount(accountId: Long) =
        database.accountDao().deleteById(id = accountId)

    override fun getAllClients(): Flow<List<ClientModel>> =
        database.clientDao().getAll()
    override suspend fun getClient(accountId: Long): ClientModel? =
        database.clientDao().getClientById(id = accountId)
    override suspend fun insertClient(accountId: Long, client: ClientModel) =
        database.clientDao().insert(ClientEntity.fromModel(model = client, id = accountId))
    override suspend fun deleteClient(accountId: Long) =
        database.clientDao().deleteById(id = accountId)
    override suspend fun updateWaterAmountClient(accountId: Long, waterAmount: Float) =
        database.clientDao().updateWaterAmount(id = accountId, waterAmount = waterAmount)

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