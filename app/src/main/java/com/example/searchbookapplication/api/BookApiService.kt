package com.example.searchbookapplication.api

import com.example.searchbookapplication.model.bookModel
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {

    @GET("/books/v1/volumes")
    suspend fun searchBooks(
        @Query("q") query: String
    ): bookModel
}
