package com.omstu.biznessapp.repository

import com.omstu.biznessapp.network.request.GroupsRequest
import com.omstu.biznessapp.network.request.TableRequest
import com.omstu.biznessapp.network.response.Group
import com.omstu.biznessapp.network.response.Table
import com.omstu.biznessapp.network.response.UpdateResponse
import io.reactivex.Flowable
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface NetworkRepository {

    @POST("fnppList")
    fun getGroups(@Body() groupsRequest: GroupsRequest): Flowable<List<Group>>

    @POST("getListStruct")
    fun getTable(@Body() tableRequest: TableRequest): Flowable<Table>

    @Multipart
    @POST("update")
    fun updateTable(
            @Part tableData: MultipartBody.Part,
            @Part signData: MultipartBody.Part,
            @Part signImage: MultipartBody.Part): Flowable<UpdateResponse>

    @POST("updateFieldRatingHours")
    fun updateTable(@Body() tableRequest: TableRequest): Flowable<UpdateResponse>
}