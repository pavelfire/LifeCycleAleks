package com.vk.directop.lifecyclealeks.aston_lesson_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.vk.directop.lifecyclealeks.R

class AstonL6Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aston_l6)

        supportFragmentManager.commit {
            replace<FirstL6Fragment>(R.id.fragmentContainer)
        }

//        supportFragmentManager
//            .beginTransaction() //начать транзакцию
//            .replace(R.id.fragmentContainer, FirstL6Fragment()) // действие
//            .commit() // закончить транзакцию
    }
}