package com.firehook.moviestore.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import com.firehook.moviestore.MovieStoreApplication
import com.firehook.moviestore.R
import com.firehook.moviestore.di.modules.network.MovieEntity
import com.firehook.moviestore.util.DateFormatter
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import javax.inject.Inject


/**
 * Created by firehook on 11.10.2020.
 * firehook21@gmail.com
 */
class MovieListFragment: Fragment(), MovieListAdapter.MovieClickListener {

    @Inject lateinit var dateFormatter: DateFormatter

    private lateinit var movieList: List<MovieEntity>
    private var selectedPosition = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MovieStoreApplication.appComponent.inject(this)
        movieList = arguments?.getParcelableArrayList(MainActivity.MOVIE_EXTRA)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MovieListAdapter(dateFormatter, movieList, this)

        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {
                val selectedHolder = recyclerView.findViewHolderForAdapterPosition(selectedPosition)
                if (selectedHolder == null || selectedHolder.itemView == null)
                    return

                sharedElements?.put(
                    R.id.image.toString(),
                    selectedHolder.itemView.findViewById(R.id.image)
                )
                sharedElements?.put(
                    R.id.name.toString(),
                    selectedHolder.itemView.findViewById(R.id.name)
                )
            }
        })

        exitTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.grid_exit_transition)

    }

    override fun onMovieClicked(movie: MovieEntity, position: Int) {
        selectedPosition = position
        val holder = recyclerView
            .findViewHolderForAdapterPosition(selectedPosition)

        val imageView = holder?.itemView?.findViewById<ImageView>(R.id.image)
        imageView?.transitionName = "${imageView?.id} image"
        val textView = holder?.itemView?.findViewById<TextView>(R.id.name)
        textView?.transitionName = "${textView?.id} text"

        val transition: Transition = TransitionInflater.from(context)
            .inflateTransition(R.transition.image_shared_element_transition)
        sharedElementEnterTransition = transition

        val bundle = Bundle()
        bundle.putInt(POSITION_EXTRA, selectedPosition)
        bundle.putParcelableArrayList(MainActivity.MOVIE_EXTRA, ArrayList(movieList))
        val fragment = MoviePagerFragment()
        fragment.arguments = bundle

        fragmentManager?.beginTransaction()
            ?.setReorderingAllowed(true)
            ?.addSharedElement(imageView!!,imageView.transitionName)
            ?.addSharedElement(textView!!, textView.transitionName)
            ?.replace(
                R.id.container,
                fragment,
                MoviePagerFragment::class.java.simpleName
            )
            ?.addToBackStack(null)
            ?.commit()
    }

    companion object {
        const val POSITION_EXTRA = "position"
    }

}