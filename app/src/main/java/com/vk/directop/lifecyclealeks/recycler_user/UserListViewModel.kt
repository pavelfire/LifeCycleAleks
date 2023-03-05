package com.vk.directop.lifecyclealeks.recycler_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vk.directop.lifecyclealeks.recycler_user.model.User
import com.vk.directop.lifecyclealeks.recycler_user.model.UsersListener
import com.vk.directop.lifecyclealeks.recycler_user.model.UsersService

class UserListViewModel(
    private val usersService: UsersService
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val listener: UsersListener = {
        _users.value = it
    }

    init{
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        usersService.removeListener(listener)
    }
    fun loadUsers() {
        usersService.addListener(listener)
    }

    fun moveUser(user: User, moveBy: Int) {
        usersService.moveUser(user, moveBy)
    }

    fun deleteUser(user: User) {
        usersService.deleteUser(user)
    }
}