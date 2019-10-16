package com.android.sportnmedc.network

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import org.json.JSONException
import org.json.JSONStringer
import java.util.*

/*
{
  "data": {
     "code": 1,
     "data": { // Response Json Data
      ...
     },
     "message": "OK", // Success / Fail Message
     "accessToken": "token", // API 요청을 위한 토큰 정보
     "refreshToken": "token"  // API 요청을 위한 토큰 정보
   }
 }
*/
open class BaseData(@Expose val code: Int, @Expose val message: String, @Expose val data: JsonObject, @Expose val accessToken: String, @Expose val refreshToken: String) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}