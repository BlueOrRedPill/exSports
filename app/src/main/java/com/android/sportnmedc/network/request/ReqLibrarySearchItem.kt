package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Library - Get card search list

{
    "search" : "search",
    "resultCount" : 20,
    "order" : "asc",
    "lastCardId" : 1234
}
*/
data class ReqLibrarySearchItem(@Expose var search: String, @Expose var resultCount: Int, @Expose var order: String, @Expose var lastCardId: Long)