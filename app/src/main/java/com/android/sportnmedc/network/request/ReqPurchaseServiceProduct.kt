package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Library - Get all cards

{
    "publicKey" : "publicKey",
    "productId": 1,
    "quantity": 3
}
*/
data class ReqPurchaseServiceProduct(@Expose var publicKey: String, @Expose var productId: Int, @Expose var quantity: Int)