package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Auth - Login

{
    "email" : "abc@abc.com",
    "password" : "1234",
}
*/
data class ReqAuthLogin (@Expose var email: String, @Expose var password: String)