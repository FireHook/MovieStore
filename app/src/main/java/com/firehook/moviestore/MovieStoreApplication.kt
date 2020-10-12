package com.firehook.moviestore

import android.app.Application
import android.util.Log
import com.firehook.moviestore.di.ApplicationComponent
import com.firehook.moviestore.di.DaggerApplicationComponent
import com.firehook.moviestore.di.modules.ApplicationModule
import com.firehook.moviestore.di.modules.database.DatabaseModule
import com.firehook.moviestore.di.modules.network.NetworkModule
import timber.log.Timber

/**
 * Created by firehook on 06.10.2020.
 * firehook21@gmail.com
 */
class MovieStoreApplication: Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        Timber.d("App onCreate")

        Timber.plant(Timber.DebugTree())

        appComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .databaseModule(DatabaseModule(this))
            .build()
    }
}