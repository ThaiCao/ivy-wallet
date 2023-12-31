package com.ivy.wallet.network.request.auth

import com.google.gson.annotations.SerializedName

data class UpdateUserInfoRequest(
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("lastName")
    val lastName: String? = null
)