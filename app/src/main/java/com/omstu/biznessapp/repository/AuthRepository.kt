package com.omstu.biznessapp.repository

import com.omstu.biznessapp.network.response.AuthResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthRepository {

    @GET("exauth.php")
    fun login(
            @Query("l") login: String,
            @Query("p") password: String
    ): Flowable<AuthResponse>
}