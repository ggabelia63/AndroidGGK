package com.example.androidfinal.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(bookDto: BookDto)

    @Delete
    suspend fun deleteBook(bookDto: BookDto)


    @Query("SELECT * FROM books_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<BookDto>>
}