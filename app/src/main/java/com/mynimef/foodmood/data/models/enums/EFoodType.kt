package com.mynimef.foodmood.data.models.enums

import com.mynimef.foodmood.R

enum class EFoodType(val type: Int, val icon: Int) {
    BREAKFAST(R.string.type_food_breakfast, R.drawable.ic_food_breakfast),
    DINNER(R.string.type_food_dinner, R.drawable.ic_food_dinner),
    SUPPER(R.string.type_food_supper, R.drawable.ic_food_supper),
    SNACK1(R.string.type_food_snack1, R.drawable.ic_food_lunch),
    SNACK2(R.string.type_food_snack2, R.drawable.ic_food_lunch),
    NONE(0,0)
}