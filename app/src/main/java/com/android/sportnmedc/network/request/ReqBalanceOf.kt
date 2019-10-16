package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
{
    "publicKey": "publicKey",
}
*/
data class ReqBalanceOf(@Expose var publicKey: String)