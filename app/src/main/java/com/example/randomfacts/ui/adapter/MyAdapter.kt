package com.example.randomfacts.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.randomfacts.R
import com.example.randomfacts.databinding.HistoryItemBinding
import com.example.randomfacts.domain.model.NumbersFact

class MyAdapter(val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MyAdapter.Holder>() {
    private var factsList = listOf<NumbersFact>()

    inner class Holder(val item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {
        private val binding = HistoryItemBinding.bind(item)
        fun bind(numbersFact: NumbersFact) {
            item.setOnClickListener(this)
            with(binding) {
                numberTV.text = numbersFact.number.toString()
                factTV.text = numbersFact.text
            }
        }

        override fun onClick(p0: View?) {
            val number = factsList[adapterPosition].number
            onItemClickListener.onItemClick(number)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(factsList[position])
    }

    override fun getItemCount(): Int = factsList.size

    fun setFactsList(list: List<NumbersFact>) {
        factsList = list
    }

    fun getFactsList(): List<NumbersFact> = factsList

    interface OnItemClickListener {
        fun onItemClick(number: Int)
    }
}