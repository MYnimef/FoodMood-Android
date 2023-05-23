package com.mynimef.foodmood.data.models.enums

import com.mynimef.foodmood.R

enum class ETypeMeal(val icon: Int, val label: Int) {
    BREAKFAST(R.drawable.ic_food_breakfast, R.string.type_food_breakfast),
    BRUNCH(R.drawable.ic_food_lunch, R.string.type_food_snack1),
    LUNCH(R.drawable.ic_food_lunch, R.string.type_food_snack2),
    SUPPER(R.drawable.ic_food_supper, R.string.type_food_supper),
    DINNER(R.drawable.ic_food_dinner, R.string.type_food_dinner),
}