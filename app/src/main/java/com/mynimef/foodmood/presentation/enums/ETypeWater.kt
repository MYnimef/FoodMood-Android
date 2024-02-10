package com.mynimef.foodmood.presentation.enums

import com.mynimef.foodmood.R

enum class ETypeWater(val icon: Int, val text: Int, val amount: Float) {
    GLASS_SMALL(
        icon = R.drawable.ic_glass,
        text = R.string.ml_100,
        amount = 0.05f,
    ),
    GLASS_BIG(
        icon = R.drawable.ic_glass,
        text = R.string.ml_200,
        amount = 0.1f,
    ),
    CUP_SMALL(
        icon = R.drawable.ic_cup,
        text = R.string.ml_300,
        amount = 0.15f,
    ),
    CUP_BIG(
        icon = R.drawable.ic_cup,
        text = R.string.ml_400,
        amount = 0.2f,
    ),
    BOTTLE_SMALL(
        icon = R.drawable.ic_bottle,
        text = R.string.ml_500,
        amount = 0.25f,
    ),
    BOTTLE_BIG(
        icon = R.drawable.ic_bottle,
        text = R.string.ml_600,
        amount = 0.3f,
    ),
    ;
}