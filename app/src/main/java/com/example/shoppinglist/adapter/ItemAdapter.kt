package com.example.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.ItemObserver
import com.example.shoppinglist.R
import com.example.shoppinglist.model.Item

class ItemAdapter(private val observer: ItemObserver) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>(), Adapter {

    private val items: MutableList<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val rootView = LayoutInflater.from(context)
                .inflate(R.layout.items_list, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.isChecked.isChecked = item.isChecked
        holder.isChecked.setOnClickListener {
            item.isChecked = holder.isChecked.isChecked
            notifyItemChanged(position)
            observer.isChecked(item.id, item.isChecked)
        }
        holder.itemView.setOnClickListener { observer.onClickItem(item.id) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitItems(newItems: List<Item>) {
        items.clear()
        items.addAll(newItems)
        items.sortWith { i1: Item, i2: Item -> i2.date.compareTo(i1.date) }
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById<View>(R.id.name) as TextView
        val isChecked: CheckBox = view.findViewById<View>(R.id.isChecked) as CheckBox
    }

    override fun deleteItem(position: Int) {
        val itemId = items[position].id
        items.removeAt(position)
        notifyItemRemoved(position)
        observer.delete(itemId)
    }
}
