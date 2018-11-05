package com.omstu.biznessapp.network.response

import com.google.gson.annotations.SerializedName

data class Group(@SerializedName("nrec")
                 val nrec: String = "",
                 @SerializedName("numdoc")
                 val numdoc: String = "",
                 @SerializedName("year")
                 val year: String = "",
                 @SerializedName("semester")
                 val semester: String = "",
                 @SerializedName("status")
                 val status: String = "",
                 @SerializedName("formEdu")
                 val formEdu: String = "",
                 @SerializedName("studGroup")
                 val studGroup: String = "",
                 @SerializedName("listChair")
                 val listChair: String = "",
                 @SerializedName("listFacult")
                 val listFacult: String = "",
                 @SerializedName("discipline")
                 val discipline: String = "",
                 @SerializedName("examiner")
                 val examiner: String = "")