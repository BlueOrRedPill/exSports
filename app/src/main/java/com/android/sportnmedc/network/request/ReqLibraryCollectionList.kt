package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Library - Get collection list

{
    "targetUid" : 1234,
    "sportsType" : "type",
    "resultCount" : 20,
    "order" : "asc",
    "lastCardId" : 1234
}
*/
data class ReqLibraryCollectionList(@Expose var targetUid: Long, @Expose var sportsType:String, @Expose var resultCount: Int, @Expose var order: String, @Expose var lastCardId: Long)