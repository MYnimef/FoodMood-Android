package com.mynimef.data_local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mynimef.data_local.models.AccountEntity

@Dao
internal interface AccountDao {

    @Query("SELECT account.refresh_token FROM account WHERE account.id = :id")
    suspend fun getRefreshTokenById(id: Long): String

    @Query("SELECT * FROM account")
    suspend fun getAll(): List<AccountEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AccountEntity): Long

    @Delete
    suspend fun delete(account: AccountEntity)

    @Query("DELETE FROM account WHERE account.id = :id")
    suspend fun deleteById(id: Long)

}
