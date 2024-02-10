package com.mynimef.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mynimef.data_local.models.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: CardEntity): Long

    @Transaction
    @Query("SELECT * FROM card")
    fun getAll(): Flow<List<CardEntity>>

    @Query("DELETE FROM card")
    suspend fun deleteAll()

}