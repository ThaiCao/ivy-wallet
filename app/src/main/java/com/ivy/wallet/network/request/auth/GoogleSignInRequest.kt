package com.ivy.wallet.network.request.auth

import com.google.gson.annotations.SerializedName

data class GoogleSignInRequest(
    @SerializedName("googleIdToken")
    val googleIdToken: String,
    @SerializedName("fcmToken")
    val fcmToken: String
)