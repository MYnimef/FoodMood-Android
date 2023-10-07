package com.mynimef.foodmood.domain

private val emailRegex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?".toRegex()

fun nameChecker(value: String): Boolean {
    return value.length >= 2
}

fun passwordChecker(value: String): Boolean {
    return value.length >= 8
}

fun emailChecker(value: String): Boolean {
    return emailRegex.matches(value)
}
