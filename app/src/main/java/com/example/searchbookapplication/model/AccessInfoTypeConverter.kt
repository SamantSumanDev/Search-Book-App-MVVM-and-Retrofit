package com.example.searchbookapplication.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class AccessInfoTypeConverter {
    @TypeConverter
    fun fromAccessInfo(accessInfo: AccessInfo): String {
        // Convert AccessInfo to a String representation that can be stored in the database
        // For example:
        return Gson().toJson(accessInfo)
    }

    @TypeConverter
    fun toAccessInfo(accessInfoString: String): AccessInfo {
        // Convert the String representation back to an AccessInfo object
        // For example:
        return Gson().fromJson(accessInfoString, AccessInfo::class.java)
    }

    @TypeConverter
    fun fromSaleInfo(saleInfo: SaleInfo): String {
        // Convert SaleInfo to a String representation that can be stored in the database
        // For example:
        return Gson().toJson(saleInfo)
    }

    @TypeConverter
    fun toSaleInfo(saleInfoString: String): SaleInfo {
        // Convert the String representation back to a SaleInfo object
        // For example:
        return Gson().fromJson(saleInfoString, SaleInfo::class.java)
    }

    @TypeConverter
    fun fromSearchInfo(searchInfo: SearchInfo): String {
        // Convert SaleInfo to a String representation that can be stored in the database
        // For example:
        return Gson().toJson(searchInfo)
    }

    @TypeConverter
    fun toSearchInfo(searchInfoString: String): SearchInfo {
        // Convert the String representation back to a SearchInfo object
        // For example:
        return Gson().fromJson(searchInfoString, SearchInfo::class.java)
    }

  @TypeConverter
    fun fromVolumeInfo(volumeInfo: VolumeInfo): String {
        // Convert SaleInfo to a String representation that can be stored in the database
        // For example:
        return Gson().toJson(volumeInfo)
    }

    @TypeConverter
    fun toVolumeInfo(volumeInroString: String): VolumeInfo {
        // Convert the String representation back to a SearchInfo object
        // For example:
        return Gson().fromJson(volumeInroString, VolumeInfo::class.java)
    }


}
