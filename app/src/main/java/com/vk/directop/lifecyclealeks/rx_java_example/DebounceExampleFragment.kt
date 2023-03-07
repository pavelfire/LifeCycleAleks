package com.vk.directop.lifecyclealeks.rx_java_example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.vk.directop.lifecyclealeks.databinding.DebounceFragmentBinding
import io.reactivex.subjects.PublishSubject

class DebounceExampleFragment: Fragment() {

    private lateinit var binding: DebounceFragmentBinding
    private val editTextSubject = PublishSubject.create<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DebounceFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.debounceEditText.doOnTextChanged{ text, _, _, _ ->
            editTextSubject.onNext(text.toString())
        }

        editTextSubject.subscribe{
            Toast.makeText(requireContext(), "отправка запроса о поиске $it", Toast.LENGTH_SHORT)
                .show()
        }
    }
}