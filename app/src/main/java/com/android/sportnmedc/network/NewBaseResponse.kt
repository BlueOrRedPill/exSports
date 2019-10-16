package com.android.sportnmedc.network

import com.android.sportnmedc.network.model.CollectionTypeModel
import com.google.gson.JsonArray
import com.google.gson.JsonObject

/**
{
   "data": {
    ...
   },
   "provider": "EXSportsAPI",
   "status": 200,
   "warnings": []
 }
*/
interface NewBaseResponse<T> {
    val result: BaseData
    val warnings: List<String>
    val status: Integer
    val provider: String
    val data:T
//    val dataList: List<*>

    fun isSuccess(): Boolean {
        return (status.equals(200) || status.equals(201)) && result.code >= 0
    }
}