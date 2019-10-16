package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Wallet - Send coin

{
    "targetUid" : 1234,
    "sendExs" : 20
}
*/
data class ReqWalletSendCoin(@Expose var targetUid: Long, @Expose var sendExs: Int)