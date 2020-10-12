package com.firehook.moviestore.ui.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.firehook.moviestore.di.modules.network.MovieEntity

/**
 * Created by firehook on 12.10.2020.
 * firehook21@gmail.com
 */
class MoviePagerAdapter(fm: FragmentManager, private val items: List<MovieEntity>): FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Fragment {
        return MovieDetailFragment.newInstance(items[position])
    }
}