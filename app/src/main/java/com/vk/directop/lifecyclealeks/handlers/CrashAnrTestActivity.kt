package com.vk.directop.lifecyclealeks.handlers

//https://www.youtube.com/watch?v=e7fzvA6XCcc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.databinding.ActivityCrashAnrTestBinding

private lateinit var binding: ActivityCrashAnrTestBinding

private var timerValue = 10
private var thread: Thread? = null

class CrashAnrTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrashAnrTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btStart.setOnClickListener { startTimer() }
        binding.progressBar.max = START_VALUE

        updateUi()

    }



    override fun onDestroy() {
        stopTimer()
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    private fun startTimer(){
        thread = Thread{
            for (i in START_VALUE downTo 0){
                timerValue = i
                runOnUiThread{ updateUi()}
                Thread.sleep(1000)
            }
            runOnUiThread{stopTimer()}
        }
        thread?.start()
    }

    private fun stopTimer(){
        thread = null
        timerValue = START_VALUE
        updateUi()
    }
    private fun updateUi(){
        val timerText = resources.getQuantityString(R.plurals.seconds, timerValue, timerValue)
        binding.tvTimer.text = timerText
        binding.progressBar.progress = timerValue
        binding.btStart.isEnabled = thread == null
    }

    companion object{
        @JvmStatic private val TAG = CrashAnrTestActivity::class.java.simpleName

        @JvmStatic private val START_VALUE = 10
    }
}