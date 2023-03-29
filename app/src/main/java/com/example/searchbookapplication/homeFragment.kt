package com.example.searchbookapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchbookapplication.adapter.searchBookAdapter
import com.example.searchbookapplication.databinding.FragmentHomeBinding
import com.example.searchbookapplication.model.Item
import com.example.searchbookapplication.viewModel.MyViewModel
import com.example.searchbookapplication.viewModel.homeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class homeFragment() : Fragment(), searchBookAdapter.ClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: homeViewModel
    private lateinit var adapter: searchBookAdapter
    private lateinit var database: AppDatabase


    override fun onAttach(context: Context) {
        super.onAttach(context)
        database = AppDatabase.getInstance(context)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(homeViewModel::class.java)

        val MyViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        MyViewModel.myData.observe(viewLifecycleOwner, { data ->
            viewModel.searchBooks(data)
        })

        viewModel.books.observe(viewLifecycleOwner) { books ->
            val bookList: List<Item> = books
          adapter = searchBookAdapter(requireContext(), bookList as ArrayList<Item>)
            val recyclerView = binding.homeRecyclerView
            val layoutManager = LinearLayoutManager(activity)

            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
            adapter.clickListener = this
        }
    }

    override fun onItemClick(item: Item) {
        var existingItem: List<Item> = emptyList()
        GlobalScope.launch {
            existingItem = database.itemDao().getAllInOne(item.id)
            if (existingItem != null) {
                activity?.runOnUiThread {
                    Toast.makeText(context,"Already added in favorite list",Toast.LENGTH_SHORT).show()
                }

            } else {
                GlobalScope.launch {
                    database.itemDao().insert(item)
                    activity?.runOnUiThread {
                        Toast.makeText(context,"successfully added",Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }


}



