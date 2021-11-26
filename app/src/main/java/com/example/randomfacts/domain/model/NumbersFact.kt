package com.example.randomfacts.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomfacts.data.room.AppDB

@Entity(tableName = AppDB.TABLE_NAME)
data class NumbersFact(
    val number: Int,
    val text: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)