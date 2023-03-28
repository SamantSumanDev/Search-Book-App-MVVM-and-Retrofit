package com.example.searchbookapplication.model

data class Offer(
    val finskyOfferType: Int,
    val giftable: Boolean,
    val listPrice: ListPriceX,
    val rentalDuration: RentalDuration,
    val retailPrice: RetailPrice
)