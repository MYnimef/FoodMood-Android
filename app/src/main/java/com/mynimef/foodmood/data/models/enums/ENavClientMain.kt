package com.mynimef.foodmood.data.models.enums

enum class ENavClientMain(val route: String, val parentRoute: String? = null) {

    BOTTOM_BAR(route = "bottom_bar"),

    CREATE(route = "create"),
    CREATE_PICK_TYPE(route = "create_pick_type", parentRoute = "create"),
    CREATE_EMOTION_CARD(route = "create_emotion_card", parentRoute = "create"),

}