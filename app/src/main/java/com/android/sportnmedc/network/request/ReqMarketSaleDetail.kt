package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Market - Get sale detail

{
    "marketId" : 1234
}
*/
data class ReqMarketSaleDetail(@Expose var marketId: Long)