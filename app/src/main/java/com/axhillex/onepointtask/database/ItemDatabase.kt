package com.axhillex.onepointtask.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Item::class],
    version = 3,
    exportSchema = false
)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    
}