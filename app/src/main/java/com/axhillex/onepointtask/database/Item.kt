package com.axhillex.onepointtask.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Item(
    val title: String = "",
    val description: String? = null,
    val image: String? = null,
    var isSynced: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)
