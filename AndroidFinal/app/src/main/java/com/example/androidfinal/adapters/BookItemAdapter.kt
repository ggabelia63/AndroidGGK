package com.example.androidfinal.adapters

import android.content.Context
import android.net.Uri
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
        val description: TextView = bookView.findViewById(R.id.book_desc_item)
        val image: ImageView = bookView.findViewById(R.id.book_image_item)
        val cardView: CardView = bookView.findViewById(R.id.cardView)
        val addBtn: Button = bookView.findViewById(R.id.add_btn)
        val source : TextView = itemView.findViewById(R.id.source)
        val author : TextView = itemView.findViewById(R.id.author)
        val urlToImage : TextView = itemView.findViewById(R.id.urlToImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books.books[position]
        holder.title.text = book.title
        holder.description.text = book.description
        holder.source.text = book.source
        holder.author.text = book.author
        holder.urlToImage.text=book.urlToImage
        Picasso.get().load(Uri.parse(book.urlToImage)).into(holder.image)
        holder.addBtn.setOnClickListener {

            val bookDto = BookDto(0, book.author, book.title, book.description, book.urlToImage,book.source)

            bBooksViewModel.addBook(bookDto)
            Toast.makeText(context, "Successfully saved Article!", Toast.LENGTH_LONG).show()
        }
        holder.cardView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFavoriteFragment(
                book.description,
                book.urlToImage,
                book.title,
                book.author,
                book.source

            )
            holder.cardView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return books.books.size
    }
}