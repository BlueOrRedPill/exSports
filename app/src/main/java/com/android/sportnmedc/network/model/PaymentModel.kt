package com.android.sportnmedc.network.model

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.reflect.TypeToken
import org.json.JSONStringer

data class PaymentModel(@Expose val amount:Int, @Expose val exsBalance:Int) {
    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(s: String): PaymentModel {
            return Gson().fromJson<PaymentModel>(s, PaymentModel::class.java)
        }

        fun fromJson(jsonObject: JsonObject): PaymentModel {
            return fromJson(jsonObject.toString())
        }

        fun fromJson(objects: Object): PaymentModel {
            return fromJson(objects.toString())
        }

        fun fromJson(objects: JSONStringer): PaymentModel {
            return fromJson(objects.toString())
        }

        fun fromJsonList(s: String): List<PaymentModel> {
            val listType = object : TypeToken<List<PaymentModel>>() {}.type
            return Gson().fromJson<List<PaymentModel>>(s, listType)
        }

        fun fromJsonList(objects: Object): List<PaymentModel> {
            return fromJsonList(objects.toString())
        }

        fun fromJsonList(jsonObject: JSONStringer?): List<PaymentModel> {
            return fromJsonList(jsonObject.toString())
        }
    }
}