package com.vk.directop.lifecyclealeks.handlers

import android.os.*
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.databinding.ActivityHandleLevel2Binding
import kotlin.random.Random

class HandleLevel2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityHandleLevel2Binding

    private val handler = Handler(Looper.getMainLooper()){
        Log.d(TAG,"Processing message: ${it.what}")
        when(it.what){
            MSG_TOGGLE_BUTTON -> toggleTestButtonState()
            MSG_NEXT_RANDOM_COLOR -> nextRandomColor()
            MSG_SHOW_TOAST -> showToast()
        }
        return@Handler true
    }

    private val token = Any()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityHandleLevel2Binding.inflate(layoutInflater).also { setContentView(it.root) }

        // для каждой найденной кнопки присваиваем листенер и код будет выполняться в другом потоке
        binding.root.forEach {
            if (it is Button) it.setOnClickListener(universalButtonListener)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    private fun toggleTestButtonState(){
        binding.testButton.isEnabled = !binding.testButton.isEnabled
    }

    private fun nextRandomColor(){
        val randomColor = Random.nextInt(255*255*255)
        binding.colorView.setBackgroundColor(randomColor)
    }

    private fun showToast(){
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private val universalButtonListener = View.OnClickListener {
        Thread{
            when(it.id){
                R.id.enableDisableButton -> {
                    val message = handler.obtainMessage(MSG_TOGGLE_BUTTON)
                    handler.sendMessage(message)
                }
                R.id.randomColorButton -> {
                    val message = Message()
                    message.what = MSG_NEXT_RANDOM_COLOR
                    handler.sendMessage(message)
                }

                R.id.enableDisableDelayedButton -> {
                    val message = Message.obtain(handler, MSG_TOGGLE_BUTTON)
                    handler.sendMessageDelayed(message, DELAY)
                }
                R.id.randomColorDelayedButton -> {
                    val message = Message.obtain(handler){
                        Log.d(TAG, "Random color is called via CALLBACK")
                        nextRandomColor()
                    }
                    handler.sendMessageDelayed(message, DELAY)
                }

                R.id.randomColorTokenDelayedButton -> {
                    val message = handler.obtainMessage(MSG_NEXT_RANDOM_COLOR)
                    message.obj = token
                    handler.sendMessageDelayed(message, DELAY)
                }
                R.id.showToastButton -> {
                    val message = handler.obtainMessage(MSG_SHOW_TOAST)
                    message.obj = token
                    handler.sendMessageDelayed(message, DELAY)
                }
                R.id.cancelButton -> handler.removeCallbacksAndMessages(token)
            }
        }.start()
    }

    companion object{
        @JvmStatic private val DELAY = 2000L // milliseconds
        @JvmStatic private val TAG = HandleLevel2Activity::class.java.simpleName

        private const val MSG_TOGGLE_BUTTON = 1
        private const val MSG_NEXT_RANDOM_COLOR = 2
        private const val MSG_SHOW_TOAST = 3
    }
}