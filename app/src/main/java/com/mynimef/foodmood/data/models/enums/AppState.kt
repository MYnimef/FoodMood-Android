package com.mynimef.foodmood.data.models.enums

enum class AppState(val value: Int) {
    NONE(0),
    USER(1),
    TRAINER(2),
    ;

    companion object {
        fun fromInt(value: Int) = AppState.values().first { it.value == value }
    }
}