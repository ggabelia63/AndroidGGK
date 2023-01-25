package com.example.androidfinal.adapters

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinal.R
import com.example.androidfinal.data.BookDto
import com.example.androidfinal.data.BookViewModel
import com.squareup.picasso.Picasso
import com.example.androidfinal.fragments.DetailInfoFragmentDirections


class FavoriteBookAdapter (

    private val context: Context, private val bBooksViewModel: BookViewModel):
    RecyclerView.Adapter<FavoriteBookAdapter.ViewHolder>() {
    private var bookList = emptyList<BookDto>()
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.book_title)
        val subtitle: TextView = itemView.findViewById(R.id.book_desc)
        val image: ImageView = itemView.findViewById(R.id.book_image)
        //        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val deleteBtn: Button = itemView.findViewById(R.id.delete_btn)
        val price : TextView = itemView.findViewById(R.id.book_price)

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorite_book_item, parent, false))
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_book_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = bookList[position]
        holder.title.text = book.title
        holder.subtitle.text = book.subtitle
        holder.price.text = book.price
        Picasso.get().load(Uri.parse(book.image)).into(holder.image)


        holder.deleteBtn.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setPositiveButton("Yes"){_, _ ->
                bBooksViewModel.deleteBook(book)
                Toast.makeText(context, "Deleted ${book.title}", Toast.LENGTH_LONG).show()
                notifyItemRemoved(position)
            }
            builder.setNegativeButton("No"){_, _ ->}
            builder.setTitle("Delete ${book.title}?")
            builder.setMessage("Are you sure you want to delete ${book.title}?")
            builder.create().show()
        }
    }

    override fun getItemCount(): Int {
        return  bookList.size
    }

    fun setData(books: List<BookDto>){
        this.bookList = books
        notifyDataSetChanged()
    }
}