package com.vk.directop.lifecyclealeks.recycler_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.databinding.FragmentUserDetailBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class UserDetailFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailBinding
    private val viewModel: UserDetailsViewModel by viewModels { factory() }

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel.loadUser(requireArguments().getLong(ARG_USER_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUserDetailBinding.inflate(layoutInflater, container, false)

        viewModel.userDetails.observe(viewLifecycleOwner, Observer {
            binding.userNameTextView.text = it.user.name
            binding.userDetailsTextView.text = it.details
            if (it.user.photo.isNotBlank()) {
                Glide.with(this)
                    .load(it.user.photo)
                    .circleCrop()
                    .into(binding.photoImageView)
            } else {
                Glide.with(this)
                    .load(R.drawable.ic_user_avatar)
                    .into(binding.photoImageView)
            }

            binding.deleteButton.setOnClickListener {
                viewModel.deleteUser()
                navigator().toast(R.string.user_has_been_deleted)
                navigator().goBack()
            }
        })

        return binding.root
    }

    companion object {

        private const val ARG_USER_ID = "ARG_USER_ID"

        fun newInstance(userId: Long): UserDetailFragment{
            val fragment = UserDetailFragment()
            fragment.arguments = bundleOf(ARG_USER_ID to userId)
            return fragment
        }
    }
}