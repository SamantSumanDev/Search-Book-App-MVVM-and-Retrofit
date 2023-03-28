package com.example.searchbookapplication.repository

import com.example.searchbookapplication.api.BooksApiClient
import com.example.searchbookapplication.model.Item

class homeRepository {

    private val apiService = BooksApiClient.apiService

    suspend fun searchBooks(q: String): List<Item> {
        val response = apiService.searchBooks(q)
        return response.items
    }
}