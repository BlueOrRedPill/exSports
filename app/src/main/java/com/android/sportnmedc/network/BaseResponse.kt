package com.android.sportnmedc.network

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

interface BaseResponse {
    val result: BaseData
    val warnings: List<String>
    val status: Integer
    val provider: String

    fun isSuccess(): Boolean {
        return (status.equals(200) || status.equals(201)) && result.code >= 0
    }
}