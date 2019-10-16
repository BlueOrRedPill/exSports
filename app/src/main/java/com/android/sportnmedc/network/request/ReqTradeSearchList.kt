package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Trade - Search trade list

{
    "search" : "search", (userName, cardName, etc ...)
    "searchType" : "type",
    "resultCount" : 20,
    "order" : "asc",
    "lastTradeId" : 1234
}
*/
data class ReqTradeSearchList(@Expose var search: String, @Expose var searchType: String, @Expose var resultCount: Int, @Expose var order: String, @Expose var lastTradeId: Long)