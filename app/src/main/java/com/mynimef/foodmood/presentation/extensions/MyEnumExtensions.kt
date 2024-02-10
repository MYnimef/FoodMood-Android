package com.mynimef.foodmood.presentation.extensions

import com.mynimef.foodmood.R
import com.mynimef.domain.models.ETypeEmotion
import com.mynimef.domain.models.ETypeMeal

fun ETypeEmotion.getIcon() = when (this) {
    ETypeEmotion.VERY_BAD -> R.drawable.ic_mood_very_bad
    ETypeEmotion.BAD -> R.drawable.ic_mood_bad
    ETypeEmotion.NORMAL -> R.drawable.ic_mood_normal
    ETypeEmotion.GOOD -> R.drawable.ic_mood_good
    ETypeEmotion.GREAT -> R.drawable.ic_mood_great
}

fun ETypeMeal.getIcon() = when (this) {
    ETypeMeal.BREAKFAST -> R.drawable.ic_food_breakfast
    ETypeMeal.BRUNCH -> R.drawable.ic_food_lunch
    ETypeMeal.LUNCH -> R.drawable.ic_food_lunch
    ETypeMeal.SUPPER -> R.drawable.ic_food_supper
    ETypeMeal.DINNER -> R.drawable.ic_food_dinner
}

fun ETypeMeal.getLabel() = when (this) {
    ETypeMeal.BREAKFAST -> R.string.type_food_breakfast
    ETypeMeal.BRUNCH -> R.string.type_food_snack1
    ETypeMeal.LUNCH -> R.string.type_food_snack2
    ETypeMeal.SUPPER -> R.string.type_food_supper
    ETypeMeal.DINNER -> R.string.type_food_dinner
}