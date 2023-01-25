package com.example.androidfinal.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
data class BookDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title" , defaultValue = "0")
    val title: String,
    @ColumnInfo(name = "subtitle" , defaultValue = "0")
    val subtitle: String,
    @ColumnInfo(name = "price" , defaultValue = "0")
    val price: String,
    @ColumnInfo(name = "image" , defaultValue = "0")
    val image: String,
)
