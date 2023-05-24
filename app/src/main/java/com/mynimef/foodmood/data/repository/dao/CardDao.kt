package com.mynimef.foodmood.data.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mynimef.foodmood.data.models.database.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: CardEntity): Long

    @Transaction
    @Query("SELECT * FROM card")
    fun getAll(): Flow<List<CardEntity>>

    @Query("DELETE FROM card")
    suspend fun deleteAll()

}