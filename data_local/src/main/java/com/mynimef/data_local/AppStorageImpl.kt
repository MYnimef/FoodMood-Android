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
import com.mynimef.data_local.models.AccountEntity
import com.mynimef.domain.IAppStorage
import com.mynimef.domain.models.AccountModel
import com.mynimef.domain.models.enums.EAppState
import kotlinx.coroutines.flow.map

class AppStorageImpl(context: Context): IAppStorage {

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
        name = "foodmood-database_account"
    )
        .fallbackToDestructiveMigration()
        .build()

    override suspend fun getRefreshToken(accountId: Long) =
        database.accountDao().getRefreshTokenById(id = accountId)
    override suspend fun insertAccount(account: AccountModel) =
        setId(database.accountDao().insert(AccountEntity.fromModel(account)))
    override suspend fun deleteAccount(accountId: Long) =
        database.accountDao().deleteById(id = accountId)

    @Database(
        entities = [
            AccountEntity::class,
        ],
        version = 1,
        exportSchema = false
    )
    internal abstract class AppDatabase: RoomDatabase() {
        abstract fun accountDao(): AccountDao
    }

}