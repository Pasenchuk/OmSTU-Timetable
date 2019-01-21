package com.omstu.biznessapp.network.response

import com.google.gson.annotations.SerializedName

data class StudentItem(@SerializedName("markStudNrec")
                       val markStudNrec: String = "",
                       @SerializedName("fio")
                       val fio: String = "",
                       @SerializedName("totalStudHours")
                       var totalStudHours: Int = 0,
                       @SerializedName("percent")
                       var percent: Int = 0,
                       @SerializedName("rating")
                       var rating: Int = 0)