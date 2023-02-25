package com.vk.directop.lifecyclealeks.aston_lesson_6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.databinding.FragmentFirstL6Binding

class FirstL6Fragment : Fragment(), MyL6Listener {

    lateinit var binding: FragmentFirstL6Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstL6Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MyL6Adapter().apply {
            itemList = mockItemList
            clickAction = ::navigateToSecondFragment
            myL6Listener = this@FirstL6Fragment

        }

        binding.firstRecyclerView.adapter = adapter
    }

    fun navigateToSecondFragment(){
        parentFragmentManager.commit{
            addToBackStack(null)
            replace<SecondL6Fragment>(R.id.fragmentContainer)
        }
    }

    companion object {

        val mockItemList = listOf(
            RecyclerDto(),
                    RecyclerDto(),
                    RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(title = "Another"),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(description = "Ogogoshechka"),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(viewType = 1),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(viewType = 1),
            RecyclerDto(viewType = 1),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(),
            RecyclerDto(title = "before 3 last"),
            RecyclerDto(),
            RecyclerDto(),
        )
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstL6Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstL6Fragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun firstOnClick() {
        TODO("Not yet implemented")
    }

    override fun secondOnClick() {
        TODO("Not yet implemented")
    }

    override fun thirdOnClick() {
        TODO("Not yet implemented")
    }


}

data class RecyclerDto(
    val title: String = "Name here",
    val description: String = "Default description",
//    val viewType: Int = R.layout.second_list_item
    val viewType: Int = 2
)