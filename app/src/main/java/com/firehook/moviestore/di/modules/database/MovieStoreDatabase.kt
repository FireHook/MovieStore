package com.firehook.moviestore.di.modules.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by firehook on 11.10.2020.
 * firehook21@gmail.com
 */
@Database(entities = [Movie::class], version = 1)
abstract class MovieStoreDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

}