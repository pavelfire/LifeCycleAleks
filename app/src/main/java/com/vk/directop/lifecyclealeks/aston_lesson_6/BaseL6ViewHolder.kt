package com.vk.directop.lifecyclealeks.aston_lesson_6

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vk.directop.lifecyclealeks.databinding.FirstListItemBinding
import com.vk.directop.lifecyclealeks.databinding.SecondListItemBinding

abstract class BaseL6ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    abstract fun populate(item: RecyclerDto, clickAction: (() -> Unit)?, myL6Listener: MyL6Listener)
}

class FirstViewHolder(val binding: FirstListItemBinding): BaseL6ViewHolder(binding.root){

    override fun populate(
        item: RecyclerDto,
        clickAction: (() -> Unit)?,
        myL6Listener: MyL6Listener
    ) {
        with(binding) {
            listItemTitle.text = item.title
            listItemDescription.text = item.description

            listItemContainer.setOnClickListener {
                // два способа обработки нажатий
                clickAction?.invoke()
                myL6Listener?.firstOnClick()
            }
        }
    }


}

class SecondViewHolder(val binding: SecondListItemBinding): BaseL6ViewHolder(binding.root){

    override fun populate(
        item: RecyclerDto,
        clickAction: (() -> Unit)?,
        myL6Listener: MyL6Listener
    ) {
        with(binding) {
            listItemTitle.text = item.title
            listItemDescription.text = item.description

            listItemContainer.setOnClickListener {
                // два способа обработки нажатий
                clickAction?.invoke()
                myL6Listener?.firstOnClick()
            }
        }
    }
}
