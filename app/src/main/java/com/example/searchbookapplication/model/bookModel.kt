package com.example.searchbookapplication.model

data class bookModel(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
) {

}