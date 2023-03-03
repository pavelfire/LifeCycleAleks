package com.vk.directop.lifecyclealeks

import android.app.Application
import com.vk.directop.lifecyclealeks.read_contacts.ContactService
import com.vk.directop.lifecyclealeks.recycler_user.model.UsersService

class App : Application() {

    val usersService = UsersService()

    val contactService = ContactService()
}