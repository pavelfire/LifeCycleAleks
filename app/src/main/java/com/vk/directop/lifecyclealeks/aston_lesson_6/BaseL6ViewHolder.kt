package com.vk.directop.lifecyclealeks.aston_lesson_6

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vk.directop.lifecyclealeks.databinding.FirstListItemBinding
import com.vk.directop.lifecyclealeks.databinding.SecondListItemBinding

abstract class BaseL6ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

class FirstViewHolder(val binding: FirstListItemBinding): BaseL6ViewHolder(binding.root)

class SecondViewHolder(val binding: SecondListItemBinding): BaseL6ViewHolder(binding.root)
