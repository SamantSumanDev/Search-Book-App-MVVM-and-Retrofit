package com.example.searchbookapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchbookapplication.AppDatabase
import com.example.searchbookapplication.R
import com.example.searchbookapplication.model.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class searchBookAdapter(
    val context: Context,
    val data: ArrayList<Item>
) : RecyclerView.Adapter<searchBookAdapter.Inners>() {

    private lateinit var database: AppDatabase


    /*lateinit var clickListener: ClickListener
    interface ClickListener {
        fun onItemClick(item: Item)
    }*/
    // lateinit var itemRepository: ItemRepository


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Inners {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)

        database = AppDatabase.getInstance(context)

        return Inners(view)
    }

    override fun onBindViewHolder(holder: Inners, position: Int) {

        val item = data[position]

        /*holder.unsave.setOnClickListener {
            clickListener.onItemClick(item)
        }*/

        if (item.volumeInfo.imageLinks != null && item.volumeInfo.imageLinks.thumbnail != null) {
            Glide.with(context)
                .load(item.volumeInfo.imageLinks.thumbnail)
                .into(holder.imgBookCover)
        } else {
            holder.imgBookCover.setImageDrawable(null)
        }

        if (item.id != null) {
            holder.txtBookNo.text = item.id
        } else {
            holder.txtBookNo.text = ""
        }

        if (item.volumeInfo.pageCount != null) {
            holder.txtBookPageCount.text = item.volumeInfo.pageCount.toString()
        } else {
            holder.txtBookPageCount.text = ""
        }


        if (item.volumeInfo.title != null) {
            holder.txtBookTitle.text = item.volumeInfo.title
        } else {
            holder.txtBookTitle.text = ""
        }

        if (item.volumeInfo.authors != null) {
            holder.txtBookAuther.text = item.volumeInfo.authors.toString()
        } else {
            holder.txtBookAuther.text = ""
        }
        if (item.volumeInfo.description != null) {
            holder.txtBookDisc.text = item.volumeInfo.description
        } else {
            holder.txtBookDisc.text = ""
        }

    }

    fun removeItem(position: Int) {
        // Implement the logic to remove the item from your data source based on the position
        // In this example, we assume that you have an ArrayList named itemList that holds your data
        data.removeAt(position)
        // Notify the adapter that the data set has changed
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class Inners(view: View) : RecyclerView.ViewHolder(view) {
        val imgBookCover: ImageView = view.findViewById(R.id.imgBookCover)
        val unsave: ImageView = view.findViewById(R.id.unsave)
        val txtBookNo: TextView = view.findViewById(R.id.txtBookNo)
        val txtBookPageCount: TextView = view.findViewById(R.id.txtBookPageCount)
        val txtBookTitle: TextView = view.findViewById(R.id.txtBookTitle)
        val txtBookAuther: TextView = view.findViewById(R.id.txtBookAuther)
        val txtBookDisc: TextView = view.findViewById(R.id.txtBookDisc)

        init {
            unsave.setOnClickListener {
                val item = data[adapterPosition]
                var existingItem: List<Item>? = null
                GlobalScope.launch {
                    existingItem = database.itemDao().getAllInOne(item.id)
                }
                if (existingItem != null) {

                } else {
                    GlobalScope.launch {
                        database.itemDao().insert(item)

                    }
                }

            }


        }
    }
}
