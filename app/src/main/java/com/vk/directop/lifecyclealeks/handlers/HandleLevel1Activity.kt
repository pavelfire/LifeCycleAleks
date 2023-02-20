package com.vk.directop.lifecyclealeks.handlers

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.forEach
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.databinding.ActivityHandleLevel1Binding
import kotlin.random.Random

class HandleLevel1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityHandleLevel1Binding

    private val handler = Handler(Looper.getMainLooper())

    private val token = Any()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityHandleLevel1Binding.inflate(layoutInflater).also { setContentView(it.root) }

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
                R.id.enableDisableButton ->
                    handler.post {toggleTestButtonState()}
                R.id.randomColorButton ->
                    handler.post{nextRandomColor()}

                R.id.enableDisableDelayedButton ->
                    handler.postDelayed({toggleTestButtonState()}, DELAY)
                R.id.randomColorDelayedButton ->
                    handler.postDelayed({nextRandomColor()}, DELAY)

                R.id.randomColorTokenDelayedButton ->
                    handler.postDelayed({nextRandomColor()}, token, DELAY)
                R.id.showToastButton ->
                    handler.postDelayed({showToast()}, token, DELAY)
            }
        }.start()
    }

    companion object{
        @JvmStatic private val DELAY = 2000L // milliseconds
    }
}