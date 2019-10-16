package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Library - Get all cards

{
    "publicKey" : "key",
    "amount": 100
}
*/
data class ReqBuyCoin(@Expose var publicKey: String, @Expose var amount: Int)