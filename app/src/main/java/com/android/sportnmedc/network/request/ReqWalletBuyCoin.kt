package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Wallet - Issue coin

{
    "buyExs" : 100,
    "code" : 1234 (일회성 인증을 위한 code)
}
*/
data class ReqWalletBuyCoin(@Expose var buyExs: Int, @Expose var code: String)