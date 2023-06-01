package com.mynimef.foodmood.data.models.enums

enum class ENavClientMain(val route: String, val parentRoute: String? = null) {

    BOTTOM_BAR(route = "bottom_bar"),

    CREATE(route = "create"),
    CREATE_PICK_TYPE(route = "create_pick_type", parentRoute = "create"),
    CREATE_EMOTION_CARD(route = "create_emotion_card", parentRoute = "create"),

    SETTINGS_USER_INFO(route = "settings_user_info"),
    SETTINGS_PREFERENCES(route = "settings_preferences"),
    SETTINGS_NOTIFICATIONS(route = "settings_notifications"),

}