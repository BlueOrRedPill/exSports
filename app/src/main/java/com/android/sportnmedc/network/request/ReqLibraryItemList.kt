package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Library - Get all cards

{
    "resultCount" : 20,
    "order" : "asc",
    "lastCardId" : 1234
}
*/
data class ReqLibraryItemList(@Expose var resultCount: Int, @Expose var order: String, @Expose var lastCardId: Long)