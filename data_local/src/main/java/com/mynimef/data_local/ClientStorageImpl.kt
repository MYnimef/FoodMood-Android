package com.mynimef.data_local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mynimef.data_local.dao.CardDao
import com.mynimef.data_local.dao.ClientDao
import com.mynimef.data_local.models.CardEntity
import com.mynimef.data_local.models.ClientEntity
import com.mynimef.domain.IClientStorage
import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.ClientModel
import kotlinx.coroutines.flow.Flow

class ClientStorageImpl(context: Context): IClientStorage {

    private val database = Room.databaseBuilder(
        context = context,
        klass = ClientDatabase::class.java,
        name = "foodmood-database_client"
    )
        .fallbackToDestructiveMigration()
        .build()

    override fun getAllAccounts(): Flow<List<ClientModel>> =
        database.clientDao().getAll()

    override suspend fun getAccount(accountId: Long): ClientModel? =
        database.clientDao().getClientById(id = accountId)

    override suspend fun insertAccount(accountId: Long, client: ClientModel): Long =
        database.clientDao().insert(ClientEntity.fromModel(id = accountId, model = client))

    override suspend fun deleteAccount(accountId: Long) =
        database.clientDao().deleteById(id = accountId)

    override suspend fun updateWaterAmount(accountId: Long, waterAmount: Float) =
        database.clientDao().updateWaterAmount(id = accountId, waterAmount = waterAmount)

    override suspend fun insertCard(card: CardModel): Long =
        database.cardDao().insert(CardEntity.fromModel(card))

    override fun getAllCards(): Flow<List<CardModel>> =
        database.cardDao().getAll()

    override suspend fun deleteAllCards() =
        database.cardDao().deleteAll()

    @Database(
        entities = [
            ClientEntity::class,
            CardEntity::class,
        ],
        version = 1,
        exportSchema = false
    )
    internal abstract class ClientDatabase: RoomDatabase() {
        abstract fun clientDao(): ClientDao
        abstract fun cardDao(): CardDao
    }

}