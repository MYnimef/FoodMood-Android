package com.mynimef.foodmood.data.models.enums

enum class ENavAuth(val route: String, val parentRoute: String? = null) {

    SIGN_IN(route = "sign_in"),

    SIGN_UP(route = "sign_up"),
    SIGN_UP_FIRST(route = "sign_up_first", parentRoute = "sign_up"),
    SIGN_UP_SECOND(route = "sign_up_second", parentRoute = "sign_up"),
    SIGN_UP_THIRD(route = "sign_up_third", parentRoute = "sign_up"),
    SIGN_UP_FOURTH(route = "sign_up_fourth", parentRoute = "sign_up"),

}