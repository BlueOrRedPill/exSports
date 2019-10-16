package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Market - Setting sale card

{
    "cardId" : 1234,
    "count" : 10,
    "price" : 123
}
*/
data class ReqMarketRegister(@Expose var cardId: Long, @Expose var count: Int, @Expose var price: Int)