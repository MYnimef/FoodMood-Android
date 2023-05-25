package com.mynimef.foodmood.data.models.enums

import com.mynimef.foodmood.R

enum class EToast(val text:Int) {
    TOAST401CLIENT(text = R.string.toast_401_client),
    TOAST401SIGNIN(text = R.string.toast_401_signin),
    TOAST403(text = R.string.toast_403),
    TOAST409(text = R.string.toast_409),
    TOASTNOCON(text = R.string.toast_no_con),
}