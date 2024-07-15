package com.axhillex.onepointtask.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.axhillex.onepointtask.database.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Upsert
    suspend fun upsert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM item")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM item WHERE isSynced = 0")
    fun getUnsyncedItems(): Flow<List<Item>>
}