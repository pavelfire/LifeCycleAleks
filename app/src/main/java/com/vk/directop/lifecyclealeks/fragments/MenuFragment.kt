package com.vk.directop.lifecyclealeks.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vk.directop.lifecyclealeks.databinding.FragmentMenuBinding
import com.vk.directop.lifecyclealeks.fragments.contract.navigator

class MenuFragment: Fragment() {

    //private lateinit var options

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //options = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: BitmapFactory.Options.DEFAULT
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //outState.putParcelable(KEY_OPTIONS, options)
    }

    private fun onOpenBoxPressed(){
        navigator()
    }
}