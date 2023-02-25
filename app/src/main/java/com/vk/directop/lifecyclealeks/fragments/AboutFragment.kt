package com.vk.directop.lifecyclealeks.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vk.directop.lifecyclealeks.BuildConfig
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.databinding.FragmentAboutBinding
import com.vk.directop.lifecyclealeks.fragments.contract.HasCustomTitle
import com.vk.directop.lifecyclealeks.fragments.contract.navigator

class AboutFragment : Fragment(), HasCustomTitle {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentAboutBinding.inflate(inflater, container, false).apply {
        versionNameTextView.text = BuildConfig.VERSION_NAME
        versionCodeTextView.text = BuildConfig.VERSION_CODE.toString()
        okButton.setOnClickListener { onOkPressed() }
    }.root

    override fun getTitleRes(): Int = R.string.about

    private fun onOkPressed(){
        navigator().goBack()
    }


}