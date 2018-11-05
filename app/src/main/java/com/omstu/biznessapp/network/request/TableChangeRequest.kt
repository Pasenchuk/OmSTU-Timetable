package com.omstu.biznessapp.network.request

import com.google.gson.annotations.SerializedName

data class TableChangeRequest(@SerializedName("nrec")
                              val nrec: String = "",
                              @SerializedName("audHoursTotal")
                              val audHoursTotal: String = "",
                              @SerializedName("audHoursCurr")
                              val audHoursCurr: String = "",
                              @SerializedName("dateOfCurHours")
                              val dateOfCurHours: String = "",
                              @SerializedName("student")
                              val student: List<StudentChangeItem>)