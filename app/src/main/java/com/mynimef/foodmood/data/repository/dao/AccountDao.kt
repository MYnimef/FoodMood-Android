package com.mynimef.foodmood.data.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mynimef.foodmood.data.models.database.AccountEntity

@Dao
interface AccountDao {
    @Query("SELECT * FROM account")
    fun getAll(): List<AccountEntity>

    @Insert
    fun insert(account: AccountEntity)

    @Delete
    fun delete(account: AccountEntity)
}
