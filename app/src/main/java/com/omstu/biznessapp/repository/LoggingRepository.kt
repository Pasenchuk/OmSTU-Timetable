package com.omstu.biznessapp.repository

interface LoggingRepository {

    fun log(tag: String, message: Any)

    fun log(message: Any)

    fun logError(tag: String, message: Any)

    fun logError(message: Any)

    fun logError(error: Throwable)

    fun logLogin(successful: Boolean)

}
