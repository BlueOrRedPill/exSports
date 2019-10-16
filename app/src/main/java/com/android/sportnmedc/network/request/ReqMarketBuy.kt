package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Market - Buy item

{
    "marketId" : 1234,
    "count" : 10,
    "price" : 123
}
*/
data class ReqMarketBuy(@Expose var marketId: Long, @Expose var count: Int, @Expose var price: Int)