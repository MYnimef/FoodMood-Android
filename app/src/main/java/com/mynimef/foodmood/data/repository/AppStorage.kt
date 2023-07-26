package com.mynimef.foodmood.data.repository

import android.content.Context
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

class AppStorage(context: Context) {

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

    suspend fun getRefreshToken() = database.accountDao().getRefreshTokenById(id)
    suspend fun insertAccount(account: AccountEntity) = setId(database.accountDao().insert(account))
    suspend fun deleteAccount(id: Long) = database.accountDao().deleteById(id)
    suspend fun deleteAccount() = database.accountDao().deleteById(id)

    fun getAllClients() = database.clientDao().getAll()
    fun getClient() = database.clientDao().getClientById(id)
    suspend fun insertClient(client: ClientEntity) = database.clientDao().insert(client.copy(id = id))
    suspend fun deleteClient(id: Long) = database.clientDao().deleteById(id)
    suspend fun deleteClient() = database.clientDao().deleteById(id)
    suspend fun updateWaterAmountClient(waterAmount: Float) = database.clientDao().updateWaterAmount(id = id, waterAmount = waterAmount)

    suspend fun insertCard(card: CardEntity) = database.cardDao().insert(card)
    fun getAllCards() = database.cardDao().getAll()
    suspend fun deleteAllCards() = database.cardDao().deleteAll()

    @Database(
        entities = [
            AccountEntity::class,
            ClientEntity::class,
            TrainerEntity::class,
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