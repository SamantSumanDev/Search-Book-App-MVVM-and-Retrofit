package com.example.searchbookapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchbookapplication.model.Item
import com.example.searchbookapplication.repository.homeRepository
import kotlinx.coroutines.launch

class homeViewModel() : ViewModel() {

    private val repository = homeRepository()

    private val _books = MutableLiveData<List<Item>>()
    val books: MutableLiveData<List<Item>>
        get() = _books

    fun searchBooks(q: String) {
        viewModelScope.launch {
            try {
                val result = repository.searchBooks(q)
                _books.postValue(result)
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }



}


