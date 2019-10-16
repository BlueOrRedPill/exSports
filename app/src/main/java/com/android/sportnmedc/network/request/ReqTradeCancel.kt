package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Trade - Cancel trade

{
    "tradeId" : 1234
}
*/
data class ReqTradeCancel(@Expose var tradeId:Long)