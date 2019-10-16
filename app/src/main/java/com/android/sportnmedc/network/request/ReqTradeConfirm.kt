package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Trade - Confirm trade

{
    "tradeId" : 1234
}
*/
data class ReqTradeConfirm(@Expose var tradeId:Long)