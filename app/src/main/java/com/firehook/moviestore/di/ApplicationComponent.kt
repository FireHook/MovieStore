package com.firehook.moviestore.di

import com.firehook.moviestore.di.modules.ApplicationModule
import com.firehook.moviestore.di.modules.database.DatabaseModule
import com.firehook.moviestore.di.modules.network.NetworkModule
import com.firehook.moviestore.ui.screens.MainActivity
import com.firehook.moviestore.ui.screens.MovieDetailFragment
import com.firehook.moviestore.ui.screens.MovieListAdapter
import com.firehook.moviestore.ui.screens.MovieListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by firehook on 06.10.2020.
 * firehook21@gmail.com
 */
@Singleton
@Component(modules = [
    ApplicationModule::class,
    NetworkModule::class,
    DatabaseModule::class])
interface ApplicationComponent {

    fun inject(component: MainActivity)
    fun inject(component: MovieListAdapter)
    fun inject(component: MovieListFragment)
    fun inject(component: MovieDetailFragment)

}