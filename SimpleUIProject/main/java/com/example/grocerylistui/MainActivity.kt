package com.example.grocerylistui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var itemInput: EditText
    private lateinit var addButton: Button
    private lateinit var clearButton: Button
    private lateinit var groceryRecyclerView: RecyclerView
    private lateinit var adapter: GroceryAdapter
    private val groceryList = mutableListOf<GroceryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemInput = findViewById(R.id.itemInput)
        addButton = findViewById(R.id.addButton)
        clearButton = findViewById(R.id.clearButton)
        groceryRecyclerView = findViewById(R.id.groceryRecyclerView)

        adapter = GroceryAdapter(groceryList)
        groceryRecyclerView.layoutManager = LinearLayoutManager(this)
        groceryRecyclerView.adapter = adapter

        addButton.setOnClickListener {
            val itemText = itemInput.text.toString().trim()
            if (itemText.isNotEmpty()) {
                adapter.addItem(GroceryItem(itemText))
                itemInput.text.clear()
            }
        }

        clearButton.setOnClickListener {
            groceryList.clear()
            adapter.notifyDataSetChanged()
        }
    }
}
