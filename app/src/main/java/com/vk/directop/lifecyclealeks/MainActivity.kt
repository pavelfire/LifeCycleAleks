package com.vk.directop.lifecyclealeks

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts


const val KEY_ONE = "HELLO_KEY"
const val RESULT_KEY = "RESULT_KEY"
private const val KEY_TEXT_RECEIVED = "KEY_TEXT_RECEIVED"
const val TAG = "LifeCycleAleks"
private var receivedText = "init"

class MainActivity : AppCompatActivity() {



    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                // Handle the Intent https://developer.android.com/training/basics/intents/result
                val tvInfo1 = findViewById<TextView>(R.id.tvInfo1)
                if (intent != null) {
                    val received =
                        intent.extras?.getString(RESULT_KEY).toString()
                    if (received != "") {
                        tvInfo1.text = received
                        receivedText = received
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btLesson1 = findViewById<Button>(R.id.btLesson1)
        val tvInfo1 = findViewById<TextView>(R.id.tvInfo1)
        val btLesson2 = findViewById<Button>(R.id.btLesson2)

        receivedText = tvInfo1.text.toString()

        if (savedInstanceState != null){
            receivedText = savedInstanceState.getString(KEY_TEXT_RECEIVED, "up")
            Log.d(TAG, "get SaveInstanceState $receivedText")
            tvInfo1.text = receivedText
        }

        btLesson2.setOnClickListener {
            tvInfo1.text = receivedText
        }


        btLesson1.setOnClickListener {
            val intent1 = Intent(this, Lesson1Activity::class.java)
            intent1.putExtra(
                KEY_ONE,
                "${resources.getString(R.string.app_name)}: \n ${tvInfo1.text}"
            )
            startForResult.launch(intent1)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState Called $receivedText")
        outState.putString(KEY_TEXT_RECEIVED, receivedText)
    }
}