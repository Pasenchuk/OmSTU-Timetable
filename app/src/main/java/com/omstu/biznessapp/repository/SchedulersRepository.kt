package com.mney.wallet.repository

import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by Pasenchuk Victor on 03/06/2017
 */

class SchedulersRepository(val uiScheduler: Scheduler) {

    fun <T> networkAsyncTransformer(): FlowableTransformer<T, T> =
            FlowableTransformer { upstream ->
                upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(uiScheduler)
            }


    fun <T> computationAsyncTransformer(): ObservableTransformer<T, T> =
            ObservableTransformer { upstream ->
                upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
            }

}
