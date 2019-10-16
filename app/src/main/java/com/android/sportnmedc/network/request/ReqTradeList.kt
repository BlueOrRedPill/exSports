package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Trade - Get trade list

{
    "resultCount" : 20,
    "order" : "asc",
    "lastTradeId" : 1234
}
*/
data class ReqTradeList(@Expose var resultCount: Int, @Expose var order: String, @Expose var lastTradeId: Long)