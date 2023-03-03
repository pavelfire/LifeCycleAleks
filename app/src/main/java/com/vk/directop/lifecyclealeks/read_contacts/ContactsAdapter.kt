package com.vk.directop.lifecyclealeks.read_contacts

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.databinding.ContactItemBinding
import com.vk.directop.lifecyclealeks.databinding.ItemUserBinding
import com.vk.directop.lifecyclealeks.recycler_user.UserActionListener
import com.vk.directop.lifecyclealeks.recycler_user.UsersAdapter
import com.vk.directop.lifecyclealeks.recycler_user.model.User


interface ContactActionListener {

    fun onUserMove(user: User, moveBy: Int)

    fun onUserDelete(user: User)

    fun onUserDetails(user: User)
}

class ContactsAdapter(
    private val actionListener: ContactActionListener
) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>(), View.OnClickListener {

    var users: List<ContactDTO> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class ContactsViewHolder(
        val binding: ContactItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContactItemBinding.inflate(
            inflater,
            parent,
            false
        )

        binding.root.setOnClickListener(this)
        binding.ivProfile.setOnClickListener(this)

        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding) {
            holder.itemView.tag = user
            tvName.text = user.name
            tvPhone.text = user.phone

        }
    }

    override fun getItemCount(): Int = users.size

    override fun onClick(view: View) {
        val user = view.tag as User
        when (view.id) {
            R.id.moreImageViewButton -> {
                showPopupMenu(view)
            }
            else -> {
                actionListener.onUserDetails(user)
            }
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val context = view.context
        val user = view.tag as User
        val position = users.indexOfFirst { it.id == user.id }

        popupMenu.menu.add(0, ID_MOVE_UP, Menu.NONE, context.getString(R.string.move_up)).apply {
            isEnabled = position > 0
        }
        popupMenu.menu.add(0, ID_MOVE_DOWN, Menu.NONE, context.getString(R.string.move_down))
            .apply {
                isEnabled = position < users.size - 1
            }
        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, context.getString(R.string.remove))

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_MOVE_UP -> {
                    actionListener.onUserMove(user, -1)
                }
                ID_MOVE_DOWN -> {
                    actionListener.onUserMove(user, 1)
                }
                ID_REMOVE -> {
                    actionListener.onUserDelete(user)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }

    companion object {
        private const val ID_MOVE_UP = 1
        private const val ID_MOVE_DOWN = 2
        private const val ID_REMOVE = 3
    }
}