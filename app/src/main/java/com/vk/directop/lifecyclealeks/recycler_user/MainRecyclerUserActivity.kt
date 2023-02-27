package com.vk.directop.lifecyclealeks.recycler_user

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vk.directop.lifecyclealeks.App
import com.vk.directop.lifecyclealeks.databinding.ActivityMainRecyclerUserBinding
import com.vk.directop.lifecyclealeks.recycler_user.model.User
import com.vk.directop.lifecyclealeks.recycler_user.model.UsersListener
import com.vk.directop.lifecyclealeks.recycler_user.model.UsersService

//https://www.youtube.com/watch?v=WMVzidyoQag&list=PLRmiL0mct8WnodKkGLpBN0mfXIbAAX-Ux&index=20

class MainRecyclerUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainRecyclerUserBinding
    private lateinit var adapter: UsersAdapter

    private val usersService: UsersService
        get() = (applicationContext as App).usersService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRecyclerUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter(object: UserActionListener{
            override fun onUserMove(user: User, moveBy: Int){
                usersService.moveUser(user, moveBy)
            }

            override fun onUserDelete(user: User) {
                usersService.deleteUser(user)
            }

            override fun onUserDetails(user: User) {
                Toast.makeText(this@MainRecyclerUserActivity, "User: ${user.name}", Toast.LENGTH_SHORT).show()
            }
        })

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        usersService.addListener(usersListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener(usersListener)
    }

    private val usersListener: UsersListener = {
        adapter.users = it
    }
}