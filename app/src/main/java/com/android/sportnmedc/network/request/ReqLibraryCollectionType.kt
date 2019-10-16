package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Library - Get Collection type list

{
    "targetUid" : 5678
}
*/
data class ReqLibraryCollectionType(@Expose var targetUid: Long)