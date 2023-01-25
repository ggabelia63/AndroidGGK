package com.example.androidfinal.data

import androidx.lifecycle.LiveData

class BookRepository (private val bookDao: BookDao) {



    val readAllData: LiveData<List<BookDto>> = bookDao.readAllData()

    suspend fun addBook(bookDto: BookDto)
    {
        bookDao.addBook(bookDto)
    }

    suspend fun deleteBook(bookDto: BookDto)
    {
        bookDao.deleteBook(bookDto)
    }
}