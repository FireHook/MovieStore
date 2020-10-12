package com.firehook.moviestore.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import com.firehook.moviestore.R
import com.firehook.moviestore.di.modules.network.MovieEntity
import kotlinx.android.synthetic.main.fragment_movie_pager.*

/**
 * Created by firehook on 11.10.2020.
 * firehook21@gmail.com
 */
class MoviePagerFragment: Fragment() {

    private var position: Int = 0
    private lateinit var movieList: List<MovieEntity>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        position = arguments?.getInt(MovieListFragment.POSITION_EXTRA)!!
        movieList = arguments?.getParcelableArrayList(MainActivity.MOVIE_EXTRA)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                val fragment = viewPager.adapter?.instantiateItem(viewPager, position) as Fragment
                val view = fragment.view

                val image = view!!.findViewById<ImageView>(R.id.image)

                sharedElements?.put(
                    R.id.image.toString(),
                    view!!.findViewById(R.id.image)
                )
                sharedElements?.put(
                    R.id.name.toString(),
                    view!!.findViewById(R.id.name)
                )
            }
        })

        viewPager.adapter = MoviePagerAdapter(fragmentManager!!, movieList)
        viewPager.currentItem = position
    }

}