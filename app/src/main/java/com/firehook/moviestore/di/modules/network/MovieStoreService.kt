package com.firehook.moviestore.di.modules.network

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by firehook on 11.10.2020.
 * firehook21@gmail.com
 */
interface MovieStoreService {

    @GET("test.json")
    fun getMovies(): Observable<List<MovieEntity>>

}