package com.vk.directop.lifecyclealeks.recycler_user.model

import com.github.javafaker.Faker
import com.vk.directop.lifecyclealeks.recycler_user.UserNotFoundException
import java.util.*

typealias UsersListener = (users: List<User>) -> Unit

class UsersService {

    private var users = mutableListOf<User>()

    private val listeners = mutableSetOf<UsersListener>()

    init {
        val faker = Faker.instance()
        IMAGES.shuffle()
        val generateUsers = (1..100).map {
            User(
                id = it.toLong(),
                name = faker.name().name(),
                company = faker.company().name(),
                photo = IMAGES[it % IMAGES.size]
            )
        }
        users = generateUsers.toMutableList()
        users.add(User(101, "", "Pavel", "Aston"))
    }

    fun getUsers(): List<User> {
        return users
    }

    fun getById(id: Long): UserDetails {
        val user = users.firstOrNull { it.id == id } ?: throw UserNotFoundException()
        return UserDetails(
            user = user,
            details = Faker.instance().lorem().paragraphs(3).joinToString("\n\n")
        )
    }


    fun deleteUser(user: User) {
        val indexToDelete = users.indexOfFirst { it.id == user.id }
        if (indexToDelete != -1) {
            users.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun moveUser(user: User, moveBy: Int) {
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex >= users.size) return
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()
    }

    fun addListener(listener: UsersListener) {
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener(listener: UsersListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(users) }
    }

    companion object {
        private val IMAGES = mutableListOf(
            "https://eletmed.ru/static/main/img/tatiana_sh.jpg",
            "https://eletmed.ru/static/main/img/analys.jpg",
            "https://eletmed.ru/static/main/img/stopalcho.jpg",
            "https://eletmed.ru/static/main/img/mainbuilding.jpg",
            "https://eletmed.ru/static/main/img/elet.jpg"

        )
    }
}