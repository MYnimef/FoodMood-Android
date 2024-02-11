package com.mynimef.data_local

import android.content.Context
import android.util.Log
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AppStorageImpl(context: Context): IAppStorageRoot {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "food_mood")
    private val dataStore = context.dataStore

    private val PREFERENCE_APP_STATE = intPreferencesKey("app_state")
    override val appState = dataStore.data
        .map { preferences ->
            EAppState.fromInt(preferences[PREFERENCE_APP_STATE] ?: EAppState.AUTH.value)
        }

    override suspend fun setAppState(state: EAppState) {
        dataStore.edit { settings ->
            settings[PREFERENCE_APP_STATE] = state.value
        }
    }

    private val PREFERENCE_ID = longPreferencesKey("account_id")
    private val accountId = dataStore.data
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

    override suspend fun getRefreshToken() =
        database.accountDao().getRefreshTokenById(id = accountId.first())
    override suspend fun insertAccount(account: AccountModel) =
        setId(database.accountDao().insert(AccountEntity.fromModel(account)))
    override suspend fun deleteAccount(id: Long) =
        database.accountDao().deleteById(id)
    override suspend fun deleteAccount() =
        database.accountDao().deleteById(id = accountId.first())

    override fun getAllClients(): Flow<List<ClientModel>> =
        database.clientDao().getAll()
    override suspend fun getClient(): ClientModel {
        val id = accountId.first()
        Log.d("mynimef", id.toString())
        return database.clientDao().getClientById(id = id)
    }
    override suspend fun insertClient(client: ClientModel) =
        database.clientDao().insert(ClientEntity.fromModel(model = client, id = accountId.first()))
    override suspend fun deleteClient(id: Long) =
        database.clientDao().deleteById(id)
    override suspend fun deleteClient() =
        database.clientDao().deleteById(id = accountId.first())
    override suspend fun updateWaterAmountClient(waterAmount: Float) =
        database.clientDao().updateWaterAmount(id = accountId.first(), waterAmount = waterAmount)

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