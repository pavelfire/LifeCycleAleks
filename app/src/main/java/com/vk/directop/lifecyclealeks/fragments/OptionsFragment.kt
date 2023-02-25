package com.vk.directop.lifecyclealeks.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.vk.directop.lifecyclealeks.BuildConfig
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.databinding.FragmentAboutBinding
import com.vk.directop.lifecyclealeks.databinding.FragmentOptionsBinding
import com.vk.directop.lifecyclealeks.fragments.contract.CustomAction
import com.vk.directop.lifecyclealeks.fragments.contract.HasCustomTitle
import com.vk.directop.lifecyclealeks.fragments.contract.navigator

class OptionsFragment : Fragment(), HasCustomTitle {

    /*

    private lateinit var binding: FragmentOptionsBinding

    private lateinit var options: BitmapFactory.Options

    private lateinit var boxCountItems: List<BoxCountItem>
    private lateinit var adapter: ArrayAdapter<BoxCountItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        options = savedInstanceState?.getParcelable(<Options>(KEY_OPTIONS) ? :
        arguments?.getParcelable(ARG_OPTIONS) ?:
        throw IllegalArgumentException("You need to specify options")
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionsBinding.inflate(inflater, container, false)

        setupSpinner()
        setupCheckBox()
        updateUi()

        binding.cancelButton.setOnClickListener { onCancelPressed() }
        binding.confirmButton.setOnClickListener { onConfirmPressed() }


        return binding.root
    }

    override fun getTitleRes(): Int = R.string.options

    private fun getCustomAction() : CustomAction {
        return CustomAction(
            iconRes = R.drawable.ic_launcher_background,
            textRes = "Done",
            onCustomAction = Runnable{
                onConfirmPressed()
            }
        )
    }
    private fun setupSpinner(){
        boxCountItems = (1..6).map { BoxCountItem(it, "{it} boxes")}
        adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner,
            boxCountItems
        )
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
    }



     */
    override fun getTitleRes(): Int {
        TODO("Not yet implemented")
    }
}