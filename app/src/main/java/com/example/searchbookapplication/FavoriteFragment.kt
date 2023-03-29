package com.example.searchbookapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchbookapplication.adapter.SwipeToDeleteCallback
import com.example.searchbookapplication.adapter.searchBookAdapter
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: searchBookAdapter
    private lateinit var database: AppDatabase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        database = AppDatabase.getInstance(context)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewFavorite)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // itemDao = AppDatabase.getInstance(requireContext())

        adapter = searchBookAdapter(requireContext(), ArrayList())
        recyclerView.adapter = adapter

        val swipeToDeleteCallback = SwipeToDeleteCallback(requireContext(), adapter)
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        return view
    }


    override fun onResume() {
        super.onResume()
        GlobalScope.launch(Dispatchers.Main) {
            // val items = itemRepository.getAllItems()
            val items = database.itemDao().getAll()
            adapter.data.clear()
            adapter.data.addAll(items)
            adapter.notifyDataSetChanged()
        }
    }
}