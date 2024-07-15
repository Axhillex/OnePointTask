package com.axhillex.onepointtask

import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.axhillex.onepointtask.di.appModule
import com.axhillex.onepointtask.di.databaseModule
import com.axhillex.onepointtask.worker.SyncWorker
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

class App : Application() {
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED) // Sync only when connected
        .build()

    private val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(15, TimeUnit.MINUTES)
        .setConstraints(constraints)
        .build()

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, databaseModule)
        }
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "syncItems",
            ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )
    }
}