package com.example.androidfinal.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BooksApi {
    private const val baseUrl = "https://api.itbook.store/1.0/"
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit


    fun getInstance(): Retrofit {
        okHttpClient = OkHttpClient.Builder().build()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    val requests: BooksApiRequest by lazy {
        getInstance().create(BooksApiRequest::class.java)
    }
}