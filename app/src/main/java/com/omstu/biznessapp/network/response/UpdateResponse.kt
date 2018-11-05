package com.omstu.biznessapp.network.response

import com.google.gson.annotations.SerializedName

data class UpdateResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("error") val error: String
)