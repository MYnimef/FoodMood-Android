package com.mynimef.foodmood.data.models.enums

enum class ENavClientMain(
    val route: String,
    val isTop: Boolean = false,
    val parentRoute: String? = null
    ) {

    BOTTOM_BAR(route = "bottom_bar", isTop = true),

    CREATE(route = "create", isTop = true),
    CREATE_PICK_TYPE(route = "create_pick_type", parentRoute = "create"),
    CREATE_EMOTION_CARD(route = "create_emotion_card", parentRoute = "create"),

}