package com.firehook.moviestore.di.modules

import android.content.Context
import com.firehook.moviestore.MovieStoreApplication
import com.firehook.moviestore.util.DateFormatter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by firehook on 06.10.2020.
 * firehook21@gmail.com
 */
@Module
class ApplicationModule(private val application: MovieStoreApplication) {

    @Provides @Singleton
    fun provideApplicationContext(): Context = application.applicationContext

    @Provides @Singleton
    fun provideDateFormatter(): DateFormatter = DateFormatter()

}