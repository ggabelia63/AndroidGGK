package com.example.androidfinal.adapters

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinal.R
import com.example.androidfinal.data.BookDto
import com.example.androidfinal.data.BookViewModel
import com.example.androidfinal.fragments.HomeFragmentDirections
import com.example.androidfinal.models.Books
import com.squareup.picasso.Picasso

private lateinit var bBooksViewModel: BookViewModel
class BookItemAdapter(private val books: Books, private val bBooksViewModel: BookViewModel, private val context: Context): RecyclerView.Adapter<BookItemAdapter.ViewHolder>() {
    class ViewHolder(bookView: View) : RecyclerView.ViewHolder(bookView) {
        val title: TextView = bookView.findViewById(R.id.book_title_item)
        val subtitle: TextView = bookView.findViewById(R.id.book_desc_item)
        val image: ImageView = bookView.findViewById(R.id.book_image_item)
       // val cardView: CardView = bookView.findViewById(R.id.cardView)
        val addBtn: Button = itemView.findViewById(R.id.add_btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        Log.d("adapterCome", view.toString());
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books.books[position]

        holder.title.text = book.title
        holder.subtitle.text = book.subtitle
        Picasso.get().load(Uri.parse(book.image)).into(holder.image)


        holder.addBtn.setOnClickListener {
            Log.d("succc","saveeed")
            val bookDto = BookDto(0, book.subtitle, book.title, book.price, book.image)

            bBooksViewModel.addBook(bookDto)
            Toast.makeText(context, "Successfully saved Article!", Toast.LENGTH_LONG).show()
            val action = HomeFragmentDirections.actionHomeFragmentToFavoriteFragment(
                book.title,
                book.subtitle,
                book.price,
                book.image,
            )
            holder.addBtn.findNavController().navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return books.books.size
    }
}