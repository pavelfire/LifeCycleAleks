package com.vk.directop.lifecyclealeks.recycler_user

import com.vk.directop.lifecyclealeks.recycler_user.model.User

interface Navigator {

    fun showDetails(user: User)

    fun goBack()

    fun toast(messageRes: Int)

}