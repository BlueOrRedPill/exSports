package com.android.sportnmedc.network.model

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.reflect.TypeToken
import org.json.JSONStringer

open class CardModel(@Expose val itemId: Long, @Expose val tokenId:Int, @Expose val sportsId:String, @Expose val athletesId:Long, @Expose val editionsId:Int, @Expose val issuanceDate:String, @Expose val statusType:String, @Expose val createTimestamp:Long, @Expose val updateTimestamp:Long, @Expose val dataJSON:DataJson, @Expose val balance:Int){
    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(s: String): CardModel {
            return Gson().fromJson<CardModel>(s, CardModel::class.java)
        }

        fun fromJson(jsonObject: JsonObject): CardModel {
            return fromJson(jsonObject.toString())
        }

        fun fromJson(objects: Object): CardModel {
            return fromJson(objects.toString())
        }

        fun fromJson(objects: JSONStringer): CardModel {
            return fromJson(objects.toString())
        }

        fun fromJsonList(s: String): List<CardModel> {
            val listType = object : TypeToken<List<CardModel>>() {}.type
            return Gson().fromJson<List<CardModel>>(s, listType)
        }

        fun fromJsonList(objects: Object): List<CardModel> {
            return fromJsonList(objects.toString())
        }

        fun fromJsonList(jsonObject: JSONStringer?): List<CardModel> {
            return fromJsonList(jsonObject.toString())
        }
    }

    inner class DataJson(@Expose val card:Card, @Expose val token:Token, @Expose val sports:Sports, @Expose val edition:Edition, @Expose val athletes:Athletes) {
        inner class Card(@Expose val id:Long, @Expose val image:ImageData) {
            inner class ImageData(@Expose val back:String, @Expose val front:String)
        }
        inner class Token(@Expose val id:Int, @Expose val issuanceDate:String)
        inner class Sports(@Expose val id:String, @Expose val name:String)
        inner class Edition(@Expose val id:Long, @Expose val logo:String, @Expose val name:String)
        inner class Athletes(@Expose val id:Long, @Expose val name:Name, @Expose val height:String, @Expose val weight:String, @Expose val nationality:Nationality, @Expose val birthdayTimestamp:Long) {
            inner class Name(@Expose val last:String, @Expose val nick:String, @Expose val first:String, @Expose val middle:String)
            inner class Nationality(@Expose val id:String, @Expose val full:String, @Expose val short:String, @Expose val flagImage:String)
        }
    }
}