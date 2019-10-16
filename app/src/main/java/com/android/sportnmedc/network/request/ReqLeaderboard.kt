package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Leaderboard - Get ranking list

{
    "resultCount" : 20,
    "order" : "asc",
    "lastUId" : 1234
}
*/
data class ReqLeaderboard(@Expose var resultCount: Int, @Expose var order: String, @Expose var lastUId: Long)