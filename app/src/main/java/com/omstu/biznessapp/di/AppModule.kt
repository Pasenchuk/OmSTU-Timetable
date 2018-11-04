package com.mney.wallet.di

import android.content.Context
import com.mney.wallet.domain.LogoutInteractor
import com.mney.wallet.repository.*
import com.mney.wallet.repository.implementations.ClipboardRepositoryImpl
import com.mney.wallet.repository.implementations.ImagesRepositoryImpl
import com.mney.wallet.repository.implementations.LoggingRepositoryImpl
import com.mney.wallet.router.RouterFactory
import io.reactivex.Scheduler
import okhttp3.Interceptor
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
                     val baseUrl: String = "http://api2.mney.com/api-v2.php/",
                     val logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY,
                     networkRepositoryMock: NetworkRepository? = null,
                     val routerFactory: RouterFactory = RouterFactory(),
                     val localeRepository: () -> Locale = { Locale.getDefault() },
                     val resourceRepository: (Int) -> String = { context.getString(it) },
                     val imagesRepository: ImagesRepository = ImagesRepositoryImpl(context),
                     val clipboardRepository: ClipboardRepository = ClipboardRepositoryImpl(context),
                     val dateRepository: () -> Date = { Date() }) {


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
            val logging = HttpLoggingInterceptor().apply {
                level = logLevel
            }

            val interceptor = Interceptor { chain ->
                val apiBuilder = chain
                        .request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")

                var request = apiBuilder
                        .build()

                request = addNonEmptyQueryParam(request, "key2", localRepository.token)
                request = addNonEmptyQueryParam(request, "account", localRepository.id)
                localRepository.selectedCoin?.coinname?.let {
                    request = addNonEmptyQueryParam(request, "coinname", it)
                }

                chain.proceed(request)
            }

            val httpClient = OkHttpClient
                    .Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(logging)
                    .build()

            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build()
                    .create(NetworkRepository::class.java)
        }
    }

    fun addNonEmptyQueryParam(request: Request, name: String, value: String): Request {
        if (value.isNotEmpty()) {
            val url = request.url().newBuilder().addQueryParameter(name, value).build()
            return request.newBuilder().url(url).build()
        }
        return request
    }
}
