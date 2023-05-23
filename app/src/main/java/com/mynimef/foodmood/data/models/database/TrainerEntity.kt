package com.mynimef.foodmood.data.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trainer")
data class TrainerEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long
)