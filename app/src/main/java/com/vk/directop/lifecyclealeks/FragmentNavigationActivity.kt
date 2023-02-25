package com.vk.directop.lifecyclealeks

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.vk.directop.lifecyclealeks.databinding.ActivityFragmentNavigationBinding
import com.vk.directop.lifecyclealeks.fragments.AboutFragment
import com.vk.directop.lifecyclealeks.fragments.MenuFragment

class FragmentNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityFragmentNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

                    supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, AboutFragment())
                .commit()


        //setSupportActionBar(binding.toolbar)

//        if (savedInstanceState == null){
//            val fragment = CounterFragment.newInstance(
//                counterValue = 1,
//                quote = createQuote()
//            )
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.fragmentContainer, fragment)
//                .commit()
//        }
    }


}