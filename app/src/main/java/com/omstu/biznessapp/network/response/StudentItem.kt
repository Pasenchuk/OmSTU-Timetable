package com.omstu.biznessapp.network.response

import com.google.gson.annotations.SerializedName

data class StudentItem(@SerializedName("markStudNrec")
                       val markStudNrec: String = "",
                       @SerializedName("fio")
                       val fio: String = "",
                       @SerializedName("totalStudHours")
                       val totalStudHours: Int = 0,
                       @SerializedName("percent")
                       val percent: Int = 0,
                       @SerializedName("rating")
                       val rating: Int = 0)