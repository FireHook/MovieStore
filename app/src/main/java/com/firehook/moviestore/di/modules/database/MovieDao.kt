package com.firehook.moviestore.di.modules.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

/**
 * Created by firehook on 11.10.2020.
 * firehook21@gmail.com
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAll(): Single<List<Movie>>

    @Insert
    fun insertMovie(movie: Movie)

}