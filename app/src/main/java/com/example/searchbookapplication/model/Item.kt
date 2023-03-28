package com.example.searchbookapplication.model

import androidx.room.*

@Entity(tableName = "item")
data class Item(
    @PrimaryKey
    val id: String,
    val accessInfo: AccessInfo,
    val etag: String,

    val kind: String,
    val saleInfo: SaleInfo,
    val searchInfo: SearchInfo,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)


