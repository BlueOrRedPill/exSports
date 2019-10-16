package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
User - Update exUser password

{
    "password" : "1234"
}
*/
data class ReqUserUpdatePassword(@Expose var password:String)