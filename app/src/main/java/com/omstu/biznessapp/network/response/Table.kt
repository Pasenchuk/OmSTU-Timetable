package com.omstu.biznessapp.network.response

import com.google.gson.annotations.SerializedName

data class Table(@SerializedName("nrec")
                 val nrec: String = "",
                 @SerializedName("numdoc")
                 val numdoc: String = "",
                 @SerializedName("listFacult")
                 val listFacult: String = "",
                 @SerializedName("studGroup")
                 val studGroup: String = "",
                 @SerializedName("semester")
                 val semester: Int = 0,
                 @SerializedName("discipline")
                 val discipline: String = "",
                 @SerializedName("audHoursTotal")
                 val audHoursTotal: Int = 0,
                 @SerializedName("audHoursCurr")
                 val audHoursCurr: Int = 0,
                 @SerializedName("student")
                 val student: List<StudentItem>)