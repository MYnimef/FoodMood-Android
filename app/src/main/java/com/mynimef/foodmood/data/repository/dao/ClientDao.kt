package com.mynimef.foodmood.data.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mynimef.foodmood.data.models.database.ClientEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ClientDao {

    @Query("SELECT * FROM client")
    fun getAll(): Flow<List<ClientEntity>>

    @Query("SELECT * FROM client WHERE client.id = :id")
    fun getClientById(id: Long): Flow<ClientEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(client: ClientEntity): Long

    @Delete
    suspend fun delete(client: ClientEntity)

    @Query("DELETE FROM client WHERE client.id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE client SET water_amount = :waterAmount WHERE id = :id")
    suspend fun updateWaterAmount(id: Long, waterAmount: Float)

}