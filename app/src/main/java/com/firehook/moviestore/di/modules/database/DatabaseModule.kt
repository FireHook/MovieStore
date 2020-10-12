package com.firehook.moviestore.di.modules.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by firehook on 11.10.2020.
 * firehook21@gmail.com
 */
@Module
class DatabaseModule(private val context: Context) {

    @Provides @Singleton
    fun provideDatabase(): MovieStoreDatabase {
        return Room.databaseBuilder(context, MovieStoreDatabase::class.java, "movie-db").build()
    }

}