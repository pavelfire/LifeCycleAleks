package com.vk.directop.lifecyclealeks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HandlerExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_example)

        findViewById<Button>(R.id.btCrash).setOnClickListener {
            startActivity(Intent(this, CrashAnrTestActivity::class.java))
        }

    }
}