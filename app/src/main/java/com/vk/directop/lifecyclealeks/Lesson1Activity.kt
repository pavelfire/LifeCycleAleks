package com.vk.directop.lifecyclealeks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val KEY_COUNT = "KEY_COUNT"

class Lesson1Activity : AppCompatActivity() {

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson1)

        val hello: String = intent.extras?.getString(KEY_ONE).toString()

        val tvFromMainActivity = findViewById<TextView>(R.id.tvFromMainActivity)
        val showCount = findViewById<TextView>(R.id.show_count)
        val buttonSend = findViewById<Button>(R.id.button_send)
        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonCount = findViewById<Button>(R.id.button_count)
        val editText = findViewById<EditText>(R.id.editText)
        val btZero = findViewById<Button>(R.id.btZero)

        tvFromMainActivity.text = hello

        if (savedInstanceState != null){
            count = savedInstanceState.getInt(KEY_COUNT, 0)
            showCount.text = count.toString()
        }

        buttonSend.setOnClickListener {
            val result = Intent().putExtra(
                RESULT_KEY,
                editText.text.toString()
            )
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        buttonToast.setOnClickListener {
            val toast: Toast = Toast.makeText(
                this, R.string.toast_message,
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
        buttonCount.setOnClickListener {
            count++
            showCount.text = count.toString()

        }
        btZero.setOnClickListener {
            count = 0
            showCount.text = count.toString()
        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState Count = $count")
        outState.putInt(KEY_COUNT, count)
    }
}