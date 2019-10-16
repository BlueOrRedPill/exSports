package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
User - Update exUser profile

{
    "name" : "dongho",
    "bio" : "bio"
}
*/
data class ReqUserUpdateProfile(@Expose var name: String, @Expose var bio: String)