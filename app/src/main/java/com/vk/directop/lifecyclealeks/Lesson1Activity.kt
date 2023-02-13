package com.vk.directop.lifecyclealeks

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Lesson1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson1)

        val hello: String = intent.extras?.getString(KEY_ONE).toString()

        val tvFromMainActivity = findViewById<TextView>(R.id.tvFromMainActivity)
        val buttonSend = findViewById<Button>(R.id.button_send)
        val editText = findViewById<EditText>(R.id.editText)

        tvFromMainActivity.text = hello

        buttonSend.setOnClickListener {
            val result = Intent().putExtra(
                RESULT_KEY,
                editText.text.toString()
            )
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }
}