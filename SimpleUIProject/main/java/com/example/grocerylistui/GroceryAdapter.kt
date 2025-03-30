package com.example.grocerylistui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class GroceryAdapter(private val items: MutableList<GroceryItem>) :
    RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {

    class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val itemName: TextView = itemView.findViewById(R.id.itemName)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grocery, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.name
        holder.checkBox.isChecked = item.isObtained

        updateStrikeThrough(holder.itemName, item.isObtained)

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.isObtained = isChecked
            updateStrikeThrough(holder.itemName, isChecked)
        }

        holder.deleteButton.setOnClickListener {
            items.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: GroceryItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    private fun updateStrikeThrough(textView: TextView, isChecked: Boolean) {
        textView.paintFlags = if (isChecked)
            textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        else
            textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}
