package com.example.searchbookapplication

import android.content.Context
import android.database.Cursor
import androidx.room.*
import com.example.searchbookapplication.model.AccessInfoTypeConverter
import com.example.searchbookapplication.model.Item

@Database(entities = [Item::class], version = 1)
@TypeConverters(AccessInfoTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "my_database"
                ).build()
                instance = newInstance
                newInstance
            }
        }
    }
}

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM Item")
    suspend fun getAll(): List<Item>


}

