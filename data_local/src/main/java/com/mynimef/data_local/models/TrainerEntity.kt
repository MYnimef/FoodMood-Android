package com.mynimef.data_local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trainer")
internal data class TrainerEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long
)