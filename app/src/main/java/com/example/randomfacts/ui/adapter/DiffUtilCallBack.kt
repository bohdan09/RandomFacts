package com.example.randomfacts.ui.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.randomfacts.domain.model.NumbersFact

class DiffUtilCallBack(
    val oldList: List<NumbersFact>,
    val newList: List<NumbersFact>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        Log.d("TAG", "areItemsTheSame: ${oldItem.id == newItem.id}")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}