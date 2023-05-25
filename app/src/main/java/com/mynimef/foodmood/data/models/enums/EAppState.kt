package com.mynimef.foodmood.data.models.enums

enum class EAppState(val value: Int) {
    AUTH(0),
    CLIENT(1),
    TRAINER(2),
    ;

    companion object {
        fun fromInt(value: Int) = EAppState.values().first { it.value == value }
    }
}