package com.vk.directop.lifecyclealeks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import com.vk.directop.lifecyclealeks.databinding.ActivityHandleLevel1Binding
import kotlin.random.Random

class HandleLevel1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityHandleLevel1Binding

    private val handler = Handler(Looper.getMainLooper())

    private val token = Any()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityHandleLevel1Binding.inflate(layoutInflater).also { setContentView(it.root) }
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

    private val universalButtonListener = View.OnClickListener {
        Thread{
            when(it.id){
                R.id.enableDisableButton ->
                    handler.post {toggleTestButtonState()}
            }
        }
    }
}