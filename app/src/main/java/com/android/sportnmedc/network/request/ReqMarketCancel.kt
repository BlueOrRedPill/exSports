package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Market - Cancel item

{
    "marketId" : 1234
}
*/
data class ReqMarketCancel(@Expose var marketId: Long)