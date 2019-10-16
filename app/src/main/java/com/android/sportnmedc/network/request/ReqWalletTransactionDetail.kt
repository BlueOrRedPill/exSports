package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Wallet - Get transaction detail

{
    "transactionId" : 1568797659
}
*/
data class ReqWalletTransactionDetail(@Expose var transactionId: Long)