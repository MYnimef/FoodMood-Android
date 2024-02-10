package com.mynimef.foodmood.data.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mynimef.domain.models.ClientModel

@Entity(tableName = "client")
data class ClientEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "name")
    override val name: String,

    @ColumnInfo(name = "track_food")
    override val trackFood: Boolean,

    @ColumnInfo(name = "track_water")
    override val trackWater: Boolean,

    @ColumnInfo(name = "track_weight")
    override val trackWeight: Boolean,

    @ColumnInfo(name = "water_amount")
    override var waterAmount: Float

): ClientModel {

    companion object {

        fun fromModel(model: ClientModel, id: Long) = ClientEntity(
            id = id,
            name = model.name,
            trackFood = model.trackFood,
            trackWater = model.trackWater,
            trackWeight = model.trackWeight,
            waterAmount = model.waterAmount
        )

        fun fromModel(model: ClientModel) = ClientEntity(
            name = model.name,
            trackFood = model.trackFood,
            trackWater = model.trackWater,
            trackWeight = model.trackWeight,
            waterAmount = model.waterAmount
        )

    }

}
