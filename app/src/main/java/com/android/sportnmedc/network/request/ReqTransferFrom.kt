package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
{
    "fromPublicKey": "from",
    "toPublicKey", "to",
    "amount", 100
}
*/
data class ReqTransferFrom(@Expose var fromPublicKey: String, @Expose var toPublicKey: String, @Expose var amount: Int)