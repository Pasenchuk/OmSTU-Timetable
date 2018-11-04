package com.omstu.biznessapp.app

import android.app.Application
import com.jakewharton.picasso.OkHttp3Downloader
import com.omstu.biznessapp.di.AppModule
import com.omstu.biznessapp.BuildConfig
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class MneyApp : Application() {

    lateinit var appModule: AppModule
        private set

    override fun onCreate() {
        super.onCreate()

//        Fabric.with(this, Crashlytics())

        initPicasso()

        val logLevel = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        appModule = AppModule(context = applicationContext,
                uiScheduler = AndroidSchedulers.mainThread(),
                logLevel = logLevel)
    }

    private fun initPicasso() {
        val okHttpClient = OkHttpClient
                .Builder()
                .cache(Cache(cacheDir, 200 * 1024 * 1024)) //200 MB cache, use Integer.MAX_VALUE if it is too low
                .build()

        val picasso = Picasso.Builder(this)
                .downloader(OkHttp3Downloader(okHttpClient))
                .indicatorsEnabled(BuildConfig.DEBUG)
                .build()
        Picasso.setSingletonInstance(picasso)
    }

}
