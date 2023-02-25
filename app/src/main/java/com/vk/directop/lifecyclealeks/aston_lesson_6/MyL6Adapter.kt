package com.vk.directop.lifecyclealeks.aston_lesson_6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vk.directop.lifecyclealeks.databinding.RecyclerItemLes6Binding

class MyL6Adapter: RecyclerView.Adapter<MyL6Adapter.MyL6ViewHolder>() {

    var clickAction: (() -> Unit)? = null
    var myL6Listener: MyL6Listener? = null
    var itemList: List<RecyclerDto> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyL6ViewHolder {
        val binding = RecyclerItemLes6Binding.inflate(LayoutInflater.from(parent.context))

        return MyL6ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyL6ViewHolder, position: Int) {
        with(holder.binding){
            listItemTitle.text = itemList[position].title
            listItemDescription.text = itemList[position].description

            listItemContainer.setOnClickListener {
                // два способа обработки нажатий
                clickAction?.invoke()
                myL6Listener?.firstOnClick()
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyL6ViewHolder(
        val binding: RecyclerItemLes6Binding
    ) : RecyclerView.ViewHolder(binding.root)
}