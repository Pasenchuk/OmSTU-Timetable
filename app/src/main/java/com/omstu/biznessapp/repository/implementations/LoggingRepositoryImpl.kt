package com.omstu.biznessapp.repository.implementations

import android.util.Log
import com.omstu.biznessapp.repository.LoggingRepository


class LoggingRepositoryImpl : LoggingRepository {

    override fun logLogin(successful: Boolean) {
//        Answers.getInstance().logLogin(LoginEvent()
//                .putSuccess(successful))
    }

    override fun log(tag: String, message: Any) {
        Log.d(tag, message.toString())
    }

    override fun log(message: Any) {
        Log.d(Exception().stackTrace[1].className, message.toString())
    }

    override fun logError(tag: String, message: Any) {
        Log.e(tag, message.toString())
    }

    override fun logError(message: Any) {
        Log.e(Exception().stackTrace[1].className, message.toString())
    }

    override fun logError(error: Throwable) {
        Log.e(error.stackTrace[0].className, error.message ?: error.javaClass.name)
//        Crashlytics.logException(error)
    }

}
