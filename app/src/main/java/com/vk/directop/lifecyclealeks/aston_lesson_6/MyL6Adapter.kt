package com.vk.directop.lifecyclealeks.aston_lesson_6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.databinding.FirstListItemBinding
import com.vk.directop.lifecyclealeks.databinding.RecyclerItemLes6Binding
import com.vk.directop.lifecyclealeks.databinding.SecondListItemBinding

//class MyL6Adapter : RecyclerView.Adapter<MyL6Adapter.MyL6ViewHolder>() {
class MyL6Adapter : RecyclerView.Adapter<BaseL6ViewHolder>() {

    var clickAction: (() -> Unit)? = null
    var myL6Listener: MyL6Listener? = null
    var itemList: List<RecyclerDto> = emptyList()

    override fun getItemViewType(position: Int) = itemList[position].viewType


    //    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyL6ViewHolder {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseL6ViewHolder {
        //val binding = RecyclerItemLes6Binding.inflate(LayoutInflater.from(parent.context))
        //return  MyL6ViewHolder(binding)
        return when (viewType) {
            1 -> FirstViewHolder(FirstListItemBinding.inflate(LayoutInflater.from(parent.context)))
            R.layout.first_list_item -> FirstViewHolder(FirstListItemBinding.inflate(LayoutInflater.from(parent.context)))
            else -> SecondViewHolder(SecondListItemBinding.inflate(LayoutInflater.from(parent.context)))
        }
    }

    override fun onBindViewHolder(holder: BaseL6ViewHolder, position: Int) {
        holder.populate(itemList[position], clickAction, myL6Listener!!)

//        with(holder.binding){
//            listItemTitle.text = itemList[position].title
//            listItemDescription.text = itemList[position].description
//
//            listItemContainer.setOnClickListener {
//                // два способа обработки нажатий
//                clickAction?.invoke()
//                myL6Listener?.firstOnClick()
//            }
//        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyL6ViewHolder(
        val binding: RecyclerItemLes6Binding
    ) : RecyclerView.ViewHolder(binding.root)


//    class FirstViewHolder(
//        val binding: RecyclerItemLes6Binding
//    ) : RecyclerView.ViewHolder(binding.root)
//
//    class SecondViewHolder(
//        val binding: RecyclerItemLes6Binding
//    ) : RecyclerView.ViewHolder(binding.root)
}