package com.axhillex.onepointtask.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.axhillex.onepointtask.repo.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject


class SyncWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    private val itemRepository by inject<ItemRepository>(ItemRepository::class.java)

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            itemRepository.syncItems()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}