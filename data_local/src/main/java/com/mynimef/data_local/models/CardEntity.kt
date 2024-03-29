package com.mynimef.data_local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.enums.ETypeEmotion
import com.mynimef.domain.models.enums.ETypeMeal

@Entity(tableName = "card")
internal data class CardEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "meal_type")
    override val mealType: ETypeMeal,

    @ColumnInfo(name = "emotion_type")
    override val emotionType: ETypeEmotion,

    @ColumnInfo(name = "emotion_description")
    override val emotionDescription: String,

    @ColumnInfo(name = "food_description")
    override val foodDescription: String?

): CardModel {
    companion object {
        fun fromModel(model: CardModel) = CardEntity(
            mealType = model.mealType,
            emotionType = model.emotionType,
            emotionDescription = model.emotionDescription,
            foodDescription = model.foodDescription,
        )
    }
}