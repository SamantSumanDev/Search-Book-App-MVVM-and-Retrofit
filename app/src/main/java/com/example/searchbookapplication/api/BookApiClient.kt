package com.example.searchbookapplication.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BooksApiClient {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    val apiService: BooksApiService by lazy {
        val okHttpClient = OkHttpClient.Builder().build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApiService::class.java)

    }
}
