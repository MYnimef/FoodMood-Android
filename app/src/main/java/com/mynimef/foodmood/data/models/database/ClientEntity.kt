package com.mynimef.foodmood.data.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
data class ClientEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "track_food")
    val trackFood: Boolean,

    @ColumnInfo(name = "track_water")
    val trackWater: Boolean,

    @ColumnInfo(name = "track_weight")
    val trackWeight: Boolean,

    @ColumnInfo(name = "water_amount")
    var waterAmount: Float

)
