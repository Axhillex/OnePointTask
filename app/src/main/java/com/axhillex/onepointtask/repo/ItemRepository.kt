package com.axhillex.onepointtask.repo

import com.axhillex.onepointtask.database.ItemDao
import com.axhillex.onepointtask.database.Item
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

class ItemRepository(
    private val itemDao: ItemDao,
    firestore: FirebaseFirestore
) {
    suspend fun upsertItem(item: Item) = itemDao.upsert(item)
    suspend fun deleteItem(item: Item) = itemDao.delete(item)
    fun getAllItems(): Flow<List<Item>> = itemDao.getAllItems()

    private val itemsCollection = firestore.collection("Item")

    suspend fun syncItems() {
        itemDao.getUnsyncedItems().collect { unSyncedItems ->
            unSyncedItems.forEach { item ->
                itemsCollection.document(item.id.toString()).set(item.copy(isSynced = true))
            }
        }
    }
}