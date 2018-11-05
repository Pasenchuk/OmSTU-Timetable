package com.omstu.biznessapp.di

import android.content.Context
import com.omstu.biznessapp.domain.LogoutInteractor
import com.omstu.biznessapp.repository.*
import com.omstu.biznessapp.repository.implementations.ClipboardRepositoryImpl
import com.omstu.biznessapp.repository.implementations.ImagesRepositoryImpl
import com.omstu.biznessapp.repository.implementations.LoggingRepositoryImpl
import com.omstu.biznessapp.router.RouterFactory
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


open class AppModule(val context: Context,
                     val loggingRepository: LoggingRepository = LoggingRepositoryImpl(),
                     val uiScheduler: Scheduler,
                     val baseUrl: String = "http://195.69.204.200:10018/",
                     val logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY,
                     networkRepositoryMock: NetworkRepository? = null,
                     val routerFactory: RouterFactory = RouterFactory(),
                     val localeRepository: () -> Locale = { Locale.getDefault() },
                     val resourceRepository: (Int) -> String = { context.getString(it) },
                     val imagesRepository: ImagesRepository = ImagesRepositoryImpl(context),
                     val clipboardRepository: ClipboardRepository = ClipboardRepositoryImpl(context),
                     val dateRepository: () -> Date = { Date() }) {


    val httpClient = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
            .build()

    val localRepository by lazy { LocalRepository(context) }
    val logoutInteractor by lazy { LogoutInteractor(localRepository) }
    val schedulersRepository by lazy { SchedulersRepository(uiScheduler) }


//    val networkInteractor by lazy {
//        NetworkInteractor(networkRepository,
//                schedulersRepository,
//                databaseRepositoryFactory)
//    }

    val networkRepository: NetworkRepository by lazy {

        if (networkRepositoryMock != null)
            networkRepositoryMock
        else {

            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build()
                    .create(NetworkRepository::class.java)
        }
    }


    val authRepository: AuthRepository by lazy {

        Retrofit.Builder()
                .baseUrl("https://omgtu.ru/ecab/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
                .create(AuthRepository::class.java)
    }

    fun addNonEmptyQueryParam(request: Request, name: String, value: String): Request {
        if (value.isNotEmpty()) {
            val url = request.url().newBuilder().addQueryParameter(name, value).build()
            return request.newBuilder().url(url).build()
        }
        return request
    }
}
