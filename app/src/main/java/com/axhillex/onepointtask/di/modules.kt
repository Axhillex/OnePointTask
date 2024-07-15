package com.axhillex.onepointtask.di

import android.content.Context
import androidx.room.Room
import com.axhillex.onepointtask.database.ItemDatabase
import com.axhillex.onepointtask.repo.ItemRepository
import com.axhillex.onepointtask.vm.ItemViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private fun provideItemDatabase(context: Context) =
    Room.databaseBuilder(
        context,
        ItemDatabase::class.java,
        "item_database"
    ).build()

private fun provideItemDao(itemDatabase: ItemDatabase) = itemDatabase.itemDao()

private fun provideFirebaseFireStore() = FirebaseFirestore.getInstance()

val databaseModule = module {
    singleOf(::provideItemDatabase)
    singleOf(::provideItemDao)
}

val appModule = module {
    singleOf(::provideFirebaseFireStore)
    singleOf(::ItemRepository)
    viewModelOf(::ItemViewModel)
}