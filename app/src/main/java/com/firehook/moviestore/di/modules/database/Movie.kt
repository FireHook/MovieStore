package com.firehook.moviestore.di.modules.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by firehook on 11.10.2020.
 * firehook21@gmail.com
 */
@Entity(tableName = "movie")
class Movie {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "itemId")        lateinit var itemId: String
    @ColumnInfo(name = "name")          lateinit var name: String
    @ColumnInfo(name = "image")         lateinit var image: String
    @ColumnInfo(name = "description")   lateinit var description: String
    @ColumnInfo(name = "time")          lateinit var time: String

}