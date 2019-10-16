package com.android.sportnmedc.network.model

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.reflect.TypeToken
import org.json.JSONStringer

open class CardDataModel(@Expose val t:token, @Expose val marketId: Long, @Expose val owner: UserModel, @Expose val cardId: Long, @Expose val cardName: String, @Expose val cardImage: String, @Expose val cardClass: String, @Expose val sportsType: Int, @Expose val sportsName: String, @Expose val editionName: String, @Expose val editionLogo: String, @Expose val country: String, @Expose val createTimestamp: Long, @Expose val fights: Int, @Expose val win: Int, @Expose val lose: Int, @Expose val draw: Int, @Expose val price: String, @Expose val count: Int) {
    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(s: String): CardDataModel {
            return Gson().fromJson<CardDataModel>(s, CardDataModel::class.java)
        }

        fun fromJson(jsonObject: JsonObject): CardDataModel {
            return fromJson(jsonObject.toString())
        }

        fun fromJson(objects: Object): CardDataModel {
            return fromJson(objects.toString())
        }

        fun fromJson(objects: JSONStringer): CardDataModel {
            return fromJson(objects.toString())
        }

        fun fromJsonList(s: String): List<CardDataModel> {
            val listType = object : TypeToken<List<CardDataModel>>() {}.type
            return Gson().fromJson<List<CardDataModel>>(s, listType)
        }

        fun fromJsonList(objects: Object): List<CardDataModel> {
            return fromJsonList(objects.toString())
        }

        fun fromJsonList(jsonObject: JSONStringer?): List<CardDataModel> {
            return fromJsonList(jsonObject.toString())
        }
    }

//    "token":{
//        "id":1000019,
//        "issuanceDate":""
//    }

    inner class token(@Expose val id:Long, @Expose val issuanceDate:String)
}