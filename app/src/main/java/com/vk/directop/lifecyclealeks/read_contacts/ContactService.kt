package com.vk.directop.lifecyclealeks.read_contacts

import android.provider.ContactsContract
import android.util.Log
import com.vk.directop.lifecyclealeks.recycler_user.model.User

typealias ContactListener = (users: List<ContactDTO>) -> Unit

class ContactService {

    private var users = mutableListOf<ContactDTO>()

    private val listeners = mutableSetOf<ContactListener>()

    init {

        users.add(ContactDTO(1,"Herename", "80454545"))
        users.add(ContactDTO(1,"Herename", "80454545"))
        users.add(ContactDTO(1,"Herename", "80454545"))

    }

    fun addListener(listener: ContactListener) {
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener(listener: ContactListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(users) }
    }
}
