package com.android.sportnmedc.network.model

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.reflect.TypeToken
import org.json.JSONStringer
import java.util.*

data class TransactionModel (@Expose val transactionId: Long, @Expose val fromUser: UserModel, @Expose val toUser: UserModel, @Expose val timestamp: Long, @Expose val type: String, @Expose val transactionExs: String, @Expose val isComplete: String, @Expose val comment: String, @Expose val blockNumber:String, @Expose val blockHash:String, @Expose val fee:String, @Expose val price:String) {
    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(s: String): TransactionModel {
            return Gson().fromJson<TransactionModel>(s, TransactionModel::class.java)
        }

        fun fromJson(jsonObject: JsonObject): TransactionModel {
            return fromJson(jsonObject.toString())
        }

        fun fromJson(objects: Object): TransactionModel {
            return fromJson(objects.toString())
        }

        fun fromJson(objects: JSONStringer): TransactionModel {
            return fromJson(objects.toString())
        }

        fun fromJsonList(s: String): List<TransactionModel> {
            val listType = object : TypeToken<List<TransactionModel>>() {}.type
            return Gson().fromJson<List<TransactionModel>>(s, listType)
        }

        fun fromJsonList(objects: Object): List<TransactionModel> {
            return fromJsonList(objects.toString())
        }

        fun fromJsonList(jsonObject: JSONStringer?): List<TransactionModel> {
            return fromJsonList(jsonObject.toString())
        }
    }
}