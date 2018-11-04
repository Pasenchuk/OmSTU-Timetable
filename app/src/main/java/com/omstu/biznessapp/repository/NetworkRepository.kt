package com.mney.wallet.repository

import com.mney.wallet.network.*
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface NetworkRepository {
    @GET("")
    fun <T> request(@Query("action") action: String, @QueryMap params: Map<String, String> = mapOf()): Flowable<T>

    @GET(".")
    fun register(
            @Query("firstname") firstname: String,
            @Query("lastname") lastname: String,
            @Query("phone") phone: String,
            @Query("email") email: String,
            @Query("password") password: String,
            @Query("action") action: String = "create-account"
    ): Flowable<RegisterResponse>

    @GET(".")
    fun login(
            @Query("email") email: String,
            @Query("password") password: String,
            @Query("action") action: String = "login-account"
    ): Flowable<RegisterResponse>

    @GET(".")
    fun codeConfirm(
            @Query("key1") key1: String,
            @Query("action") action: String = "get-key2"
    ): Flowable<CodeConfirmResponse>

    @GET(".")
    fun userDetails(
            @Query("action") action: String = "user-details"
    ): Flowable<UserDetailsResponse>

    @GET(".")
    fun listCoins(
            @Query("action") action: String = "list-coins",
            @Query("headless") headless: String = "1"
    ): Flowable<List<Coin>>

    @GET(".")
    fun listTransactions(
            @Query("action") action: String = "list-transactions-account",
            @Query("headless") headless: String = "1"
    ): Flowable<List<Transaction>>

    @GET(".")
    fun coinInfo(
            @Query("action") action: String = "get-info",
            @Query("headless") headless: String = "1"
    ): Flowable<Coin>

    @GET(".")
    fun listAddresses(
            @Query("action") action: String = "list-addresses",
            @Query("headless") headless: String = "1"
    ): Flowable<MutableList<String>>

    @GET(".")
    fun createAddress(
            @Query("action") action: String = "create-address"
    ): Flowable<CreateAddressResponse>

    @GET(".")
    fun sendFrom(
            @Query("address") address: String,
            @Query("amount") amount: Double,
            @Query("action") action: String = "send-from"
    ): Flowable<CreateAddressResponse>
}