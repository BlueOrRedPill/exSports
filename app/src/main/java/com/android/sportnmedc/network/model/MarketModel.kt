package com.android.sportnmedc.network.model

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.reflect.TypeToken
import org.json.JSONStringer

open class MarketModel(
    @Expose var productId: Int, @Expose var owner: String, @Expose var ownerName: String, @Expose var tokenName: String, @Expose var tokenId: Int, @Expose var totalQuantity: Int,
    @Expose var quantity: Int, @Expose var perPrice: Int, @Expose var isValid: Boolean) {
    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(s: String): MarketModel {
            return Gson().fromJson<MarketModel>(s.trim(), MarketModel::class.java)
        }

        fun fromJson(jsonObject: JSONStringer?): MarketModel {
            return fromJson(jsonObject.toString())
        }

        fun fromJson(jsonObject: JsonObject?): MarketModel {
            return fromJson(jsonObject.toString())
        }

        fun fromJson(objects: Object?): MarketModel {
            return fromJson(objects.toString())
        }

        fun fromJsonList(s: String): List<MarketModel> {
            val listType = object : TypeToken<List<MarketModel>>() {}.type
            return Gson().fromJson<List<MarketModel>>(s, listType)
        }

        fun fromJsonList(objects: Object?): List<MarketModel> {
            return fromJsonList(objects.toString())
        }

        fun fromJsonList(jsonObject: JSONStringer?): List<MarketModel> {
            return fromJsonList(jsonObject.toString())
        }

    }
}