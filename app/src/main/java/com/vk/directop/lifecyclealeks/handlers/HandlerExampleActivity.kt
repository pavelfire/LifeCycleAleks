package com.vk.directop.lifecyclealeks.handlers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.vk.directop.lifecyclealeks.R

class HandlerExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_example)

        findViewById<Button>(R.id.btCrash).setOnClickListener {
            startActivity(Intent(this, CrashAnrTestActivity::class.java))
        }

        findViewById<Button>(R.id.handlersLevel1).setOnClickListener {
            startActivity(Intent(this, HandleLevel1Activity::class.java))
        }

        findViewById<Button>(R.id.handlersLevel2).setOnClickListener {
            startActivity(Intent(this, HandleLevel2Activity::class.java))
        }

    }
}