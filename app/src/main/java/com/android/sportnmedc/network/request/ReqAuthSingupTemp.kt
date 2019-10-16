package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Auth - Singup

{
    "image" : "url", (optional)
    "name" : "dongho",
    "email" : "abc@abc.com",
    "password" : "1234",
}
*/

//file: MultipartBody.Part)
data class ReqAuthSingupTemp(@Expose var name:String, @Expose var email: String, @Expose var password: String)