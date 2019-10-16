package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Library - Get all cards

{
    "tokenId": 1234
}
*/
data class ReqTokenCardDetail(@Expose var tokenId: Int?)