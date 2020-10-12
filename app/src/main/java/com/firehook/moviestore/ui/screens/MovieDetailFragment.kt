package com.firehook.moviestore.ui.screens

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.firehook.moviestore.MovieStoreApplication
import com.firehook.moviestore.R
import com.firehook.moviestore.di.modules.network.MovieEntity
import com.firehook.moviestore.util.DateFormat
import com.firehook.moviestore.util.DateFormatter
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by firehook on 12.10.2020.
 * firehook21@gmail.com
 */
class MovieDetailFragment: Fragment() {

    @Inject lateinit var dateFormatter: DateFormatter

    private lateinit var movie: MovieEntity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MovieStoreApplication.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name.text = movie.name
        date.text = dateFormatter.format(movie.time.toLong(), DateFormat.FORMAT)

        Glide.with(this)
            .load(R.drawable.ic_smile)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    parentFragment?.startPostponedEnterTransition();
                    return false;
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    parentFragment?.startPostponedEnterTransition();
                    return false;
                }
            })
            .into(image)
    }

    companion object {
        fun newInstance(movie: MovieEntity): MovieDetailFragment {
            val fragment = MovieDetailFragment()
            fragment.movie = movie
            return fragment
        }
    }

}