package com.mynimef.foodmood.data.models.enums

import com.mynimef.foodmood.R

enum class EToast(val text: Int) {
    AUTH_FAILED(text = R.string.toast_401_client),
    WRONG_EMAIL_OR_PASSWORD(text = R.string.toast_401_signin),
    WRONG_INPUT(text = R.string.toast_403),
    ACCOUNT_ALREADY_EXISTS(text = R.string.toast_409),
    NO_CONNECTION(text = R.string.toast_no_con),
}