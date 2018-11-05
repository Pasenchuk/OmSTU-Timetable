package com.omstu.biznessapp.repository

import com.omstu.biznessapp.network.request.GroupsRequest
import com.omstu.biznessapp.network.request.TableRequest
import com.omstu.biznessapp.network.response.Group
import com.omstu.biznessapp.network.response.Table
import com.omstu.biznessapp.network.response.UpdateResponse
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST


interface NetworkRepository {

    @POST("/fnppList")
    fun getGroups(@Body() groupsRequest: GroupsRequest): Flowable<List<Group>>

    @POST("/getListStruct")
    fun getTable(@Body() tableRequest: TableRequest): Flowable<Table>

    @POST("/updateFieldRatingHours")
    fun updateTable(@Body() tableRequest: TableRequest): Flowable<UpdateResponse>
}