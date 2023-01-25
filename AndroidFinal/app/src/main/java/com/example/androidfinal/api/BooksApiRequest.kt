package com.example.androidfinal.api



import com.example.androidfinal.models.Books
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiRequest {
    @GET("new")
    fun getBooks(

    ): Call<Books>
}