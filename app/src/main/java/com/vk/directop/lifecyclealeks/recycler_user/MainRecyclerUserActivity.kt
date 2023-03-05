package com.vk.directop.lifecyclealeks.recycler_user

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.databinding.ActivityMainRecyclerUserBinding
import com.vk.directop.lifecyclealeks.recycler_user.model.User

//https://www.youtube.com/watch?v=WMVzidyoQag&list=PLRmiL0mct8WnodKkGLpBN0mfXIbAAX-Ux&index=20

class MainRecyclerUserActivity : AppCompatActivity() , Navigator{

    private lateinit var binding: ActivityMainRecyclerUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRecyclerUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, UsersListFragment())
                .commit()
        }

    }

    override fun showDetails(user: User) {
        Log.d("Tag", "OnUserDetails in Main")
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, UserDetailFragment.newInstance(user.id))
            .commit()
    }

    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }

    override fun toast(messageRes: Int) {
        Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
    }
}