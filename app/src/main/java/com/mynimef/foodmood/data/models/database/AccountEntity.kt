package com.mynimef.foodmood.data.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mynimef.foodmood.data.models.enums.ERole

@Entity(tableName = "account")
data class AccountEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "role")
    val role: ERole,

    @ColumnInfo(name = "refresh_token")
    val refreshToken: String,

)
