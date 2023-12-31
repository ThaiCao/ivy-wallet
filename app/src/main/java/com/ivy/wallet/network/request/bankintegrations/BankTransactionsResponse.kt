package com.ivy.wallet.network.request.bankintegrations

import com.google.gson.annotations.SerializedName
import com.ivy.wallet.model.bankintegrations.SETransaction

data class BankTransactionsResponse(
    @SerializedName("transactions")
    val transactions: List<SETransaction>
)