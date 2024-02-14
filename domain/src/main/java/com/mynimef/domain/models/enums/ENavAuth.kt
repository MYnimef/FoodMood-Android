package com.mynimef.domain.models.enums

enum class ENavAuth(val route: String, val parentRoute: String? = null) {

    SIGN_IN(route = "sign_in"),

    SIGN_UP(route = "sign_up"),
    SIGN_UP_1(route = "sign_up_1", parentRoute = "sign_up"),
    SIGN_UP_2(route = "sign_up_2", parentRoute = "sign_up"),
    SIGN_UP_3(route = "sign_up_3", parentRoute = "sign_up"),
    SIGN_UP_4(route = "sign_up_4", parentRoute = "sign_up"),

}