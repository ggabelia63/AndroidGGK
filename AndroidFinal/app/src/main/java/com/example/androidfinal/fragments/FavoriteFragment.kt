package com.example.androidfinal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinal.R
import com.example.androidfinal.adapters.FavoriteBookAdapter
import com.example.androidfinal.data.BookViewModel

class FavoriteFragment: Fragment() {


    private lateinit var bBooksViewModel: BookViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_favorite, container, false)
        bBooksViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        val adapter = FavoriteBookAdapter(requireContext(), bBooksViewModel)
        val recyclerView = view.findViewById<RecyclerView>(R.id.favoriteFragment_)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        bBooksViewModel.readAllData.observe(viewLifecycleOwner, Observer { books ->
            adapter.setData(books)

        })

        return view
    }


}