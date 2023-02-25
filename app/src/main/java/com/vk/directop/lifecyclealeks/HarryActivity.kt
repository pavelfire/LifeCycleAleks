package com.vk.directop.lifecyclealeks

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.vk.directop.lifecyclealeks.CounterFragment
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.databinding.ActivityHarryBinding

class HarryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHarryBinding

    private val faker = "Faker.instance()"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityHarryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null){
            val fragment = CounterFragment.newInstance(
                counterValue = 1,
                quote = createQuote()
            )
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit()
        }
    }

    fun createQuote() : String{
        return "faker.harryPotter().quote() {getScreensCount()}"
    }

    fun getScreensCount(): Int{
        return supportFragmentManager.backStackEntryCount + 1
    }
}