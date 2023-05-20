package com.mynimef.foodmood.data.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "accountId") val accountId: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "refresh_token") val refreshToken: String,
)
