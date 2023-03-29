package com.example.searchbookapplication.adapter

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.searchbookapplication.AppDatabase
import com.example.searchbookapplication.ItemDao
import com.example.searchbookapplication.model.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SwipeToDeleteCallback(context: Context, private val adapter: searchBookAdapter) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // Get the item that was swiped
        val position = viewHolder.adapterPosition
        val item = adapter.data.get(position)

        // Delete the item from the database
        val db = AppDatabase.getInstance(viewHolder.itemView.context)
        GlobalScope.launch {
            db.itemDao().delete(item)
        }

        // Notify the adapter that the item has been removed
        adapter.removeItem(position)
    }
}

