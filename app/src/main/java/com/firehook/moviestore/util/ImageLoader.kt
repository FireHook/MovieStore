package com.firehook.moviestore.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.provider.MediaStore
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.net.URL

/**
 * Created by firehook on 12.10.2020.
 * firehook21@gmail.com
 */
class ImageLoader {

    fun load(path: String): Observable<Bitmap?> {

        return Observable.fromCallable { BitmapFactory.decodeStream(URL(path).openConnection().getInputStream()) }
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    }

}