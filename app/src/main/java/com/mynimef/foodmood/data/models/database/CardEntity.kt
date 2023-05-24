package com.mynimef.foodmood.data.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mynimef.foodmood.data.models.enums.ETypeEmotion
import com.mynimef.foodmood.data.models.enums.ETypeMeal

@Entity(tableName = "card")
data class CardEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "meal_type")
    val mealType: ETypeMeal,

    @ColumnInfo(name = "emotion_type")
    val emotionType: ETypeEmotion,

    @ColumnInfo(name = "emotion_description")
    val emotionDescription: String,

    @ColumnInfo(name = "food_description")
    val foodDescription: String?,

)