package com.firehook.moviestore.ui.screens

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firehook.moviestore.MovieStoreApplication
import com.firehook.moviestore.R
import com.firehook.moviestore.di.ApplicationComponent
import com.firehook.moviestore.di.modules.network.MovieEntity
import com.firehook.moviestore.util.DateFormat
import com.firehook.moviestore.util.DateFormatter
import com.firehook.moviestore.util.ImageLoader
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.movie_item.view.*
import javax.inject.Inject

/**
 * Created by firehook on 11.10.2020.
 * firehook21@gmail.com
 */
class MovieListAdapter (private val dateFormatter: DateFormatter,
                        private val items: List<MovieEntity>,
                        private val clickListener: MovieClickListener): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        MovieStoreApplication.appComponent.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[position]

        holder.itemView.image.setImageResource(R.drawable.ic_smile)
        ImageLoader().load(movie.image).subscribe(object : Observer<Bitmap?> {
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(t: Bitmap) {
                holder.itemView.image.setImageBitmap(t)
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {}

        })
        holder.itemView.name.text = movie.name
        holder.itemView.date.text = dateFormatter.format(movie.time.toLong(), DateFormat.FORMAT)
        holder.itemView.setOnClickListener {
            clickListener.onMovieClicked(items[position], position)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface MovieClickListener {
        fun onMovieClicked(movie: MovieEntity, position: Int)
    }
}