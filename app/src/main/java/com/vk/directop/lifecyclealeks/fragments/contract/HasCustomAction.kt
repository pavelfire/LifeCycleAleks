package com.vk.directop.lifecyclealeks.fragments.contract

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface HasCustomAction {

    fun getCustomAction(): CustomAction
}

class CustomAction(
    @DrawableRes val iconRes: Int,
    @StringRes val textRes: String,
    val onCustomAction: Runnable
)