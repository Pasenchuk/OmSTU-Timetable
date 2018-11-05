package com.omstu.biznessapp.network.request

import com.google.gson.annotations.SerializedName

data class TableRequest(@SerializedName("nrec")
                        val nrec: String = "")