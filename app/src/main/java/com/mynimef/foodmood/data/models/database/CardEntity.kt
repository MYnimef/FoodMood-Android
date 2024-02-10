package com.mynimef.foodmood.data.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mynimef.domain.models.CardModel
import com.mynimef.domain.models.ETypeEmotion
import com.mynimef.domain.models.ETypeMeal

@Entity(tableName = "card")
data class CardEntity(

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