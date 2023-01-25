package com.example.androidfinal.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinal.R
import com.example.androidfinal.adapters.BookItemAdapter
import com.example.androidfinal.api.BooksApi
import com.example.androidfinal.data.BookDto
import com.example.androidfinal.data.BookViewModel
import com.example.androidfinal.models.Book
import com.example.androidfinal.models.Books
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment() {
    private lateinit var bBooksViewModel: BookViewModel
    private var adapter: RecyclerView.Adapter<BookItemAdapter.ViewHolder>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val view = inflater.inflate(R.layout.fragment_home, container, false)

        bBooksViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        Log.d("adapter", "come")



        view.findViewById<Button>(R.id.search_btn).setOnClickListener {
            loadData(view, recyclerView)
        }

        loadData(view,recyclerView)
        return view
    }

    private fun getTextFromSearch(view : View): String? {
        val text = view.findViewById<EditText>(R.id.search).text.toString()
        return text.ifEmpty {
            Toast.makeText(view.context, "Fill the Field", Toast.LENGTH_LONG).show()
            null
        }
    }

     private fun loadData(view: View, recyclerView: RecyclerView) {

        val searchText = getTextFromSearch(view)

            val call = BooksApi.requests.getBooks()
            call.enqueue(object : Callback<Books> {
                override fun onResponse(call: Call<Books>, response: Response<Books>) {

                    when (response.isSuccessful) {
                        true -> {
                            val books = response.body()!!
                            Log.d("bookkks", books.toString())
                            recyclerView.adapter = BookItemAdapter(books, bBooksViewModel, requireContext())
                            adapter?.notifyDataSetChanged()
                        }
                        false -> Toast.makeText(context, "No response", Toast.LENGTH_LONG)
                            .show()
                    }
                }



                override fun onFailure(call: Call<Books>, t: Throwable) {
                    Toast.makeText(context, "Request failed", Toast.LENGTH_LONG).show()
                }
            })

         }
    }
