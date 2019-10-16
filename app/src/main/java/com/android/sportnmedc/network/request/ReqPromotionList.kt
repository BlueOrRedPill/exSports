package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Promotion - Get promotion list

{
    "resultCount" : 20,
    "order" : "asc",
    "lastId" : 1234
}
*/
data class ReqPromotionList(@Expose var resultCount: Int, @Expose var order: String, @Expose var lastId: Long)