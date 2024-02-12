package com.mynimef.data_local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mynimef.domain.models.ERole
import com.mynimef.domain.models.AccountModel

@Entity(tableName = "account")
internal data class AccountEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Long = 0,

    @ColumnInfo(name = "role")
    override val role: ERole,

    @ColumnInfo(name = "refresh_token")
    override val refreshToken: String

): AccountModel {
    companion object {
        fun fromModel(model: AccountModel) = AccountEntity(
            role = model.role,
            refreshToken = model.refreshToken
        )
    }
}
