package com.mynimef.foodmood.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mynimef.foodmood.data.models.database.AccountEntity
import com.mynimef.foodmood.data.models.database.CardEntity
import com.mynimef.foodmood.data.models.database.ClientEntity
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.data.repository.dao.AccountDao
import com.mynimef.foodmood.data.repository.dao.CardDao
import com.mynimef.foodmood.data.repository.dao.ClientDao

class AppStorage(context: Context) {

    private val sharedPref: SharedPreferences
    val database: AppDatabase

    init {
        sharedPref = context.getSharedPreferences("food_mood", Context.MODE_PRIVATE)
        database =  Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "foodmood-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun getSavedState() = EAppState.fromInt(sharedPref.getInt("app_state", 0))
    fun setSavedState(state: Int) = with (sharedPref.edit()) {
        putInt("app_state", state)
        apply()
    }

    fun getSavedId() = sharedPref.getLong("account_id", 0)
    fun setSavedId(id: Long) = with (sharedPref.edit()) {
        putLong("account_id", id)
        apply()
    }

    @Database(
        entities = [
            AccountEntity::class,
            ClientEntity::class,
            CardEntity::class,
        ],
        version = 1
    )
    abstract class AppDatabase: RoomDatabase() {
        protected abstract fun accountDao(): AccountDao
        protected abstract fun clientDao(): ClientDao
        protected abstract fun cardDao(): CardDao

        suspend fun getRefreshTokenById(id: Long) = accountDao().getRefreshTokenById(id)
        suspend fun insertAccount(account: AccountEntity) = accountDao().insert(account)
        suspend fun deleteAccount(id: Long) = accountDao().deleteById(id)

        suspend fun getClient(id: Long) = clientDao().getClientById(id)
        suspend fun insertClient(client: ClientEntity) = clientDao().insert(client)
        suspend fun deleteClient(id: Long) = clientDao().deleteById(id)

        suspend fun insertCard(card: CardEntity) = cardDao().insert(card)
        fun getAllCards() = cardDao().getAll()
        suspend fun deleteAllCards() = cardDao().deleteAll()
    }

}