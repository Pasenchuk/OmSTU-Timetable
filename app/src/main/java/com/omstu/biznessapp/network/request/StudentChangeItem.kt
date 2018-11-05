package com.omstu.biznessapp.network.request

import com.google.gson.annotations.SerializedName

data class StudentChangeItem(@SerializedName("markStudNrec")
                       val markStudNrec: String = "",
                             @SerializedName("totalStudHours")
                       val totalStudHours: String = "",
                             @SerializedName("percent")
                       val percent: String = "",
                             @SerializedName("rating")
                       val rating: String = "")