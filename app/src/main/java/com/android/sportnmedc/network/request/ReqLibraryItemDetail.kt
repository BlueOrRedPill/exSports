package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Library - Get card detail

{
    "cardId" : 1234
}
*/
data class ReqLibraryItemDetail(@Expose var cardId: Long)