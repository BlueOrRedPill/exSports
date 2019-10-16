package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
User - Get exUser profile

{
    "targetUid" : 5678
}
*/
data class ReqUserProfile(@Expose var targetUid: Long)