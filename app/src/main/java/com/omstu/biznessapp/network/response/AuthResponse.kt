package com.omstu.biznessapp.network.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(@SerializedName("fnpp")
                  val fnpp: String = "",
                  @SerializedName("wnpp")
                  val wnpp: List<String>,
                  @SerializedName("err")
                  val err: String = "")