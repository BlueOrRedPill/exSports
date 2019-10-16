package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Library - Get all cards

{
    "sportsId": "jiujitsu",
    "count" : 20,
    "sortOrder" : 1, // 0 = Ascending , 1 = Descending
    "lastId" : 1234
}
*/
data class ReqCardList(@Expose var sportsId: String, @Expose var count: Int, @Expose var sortOrder: Int, @Expose var lastId: Long)