package com.android.sportnmedc.network.model

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.reflect.TypeToken
import org.json.JSONStringer

open class UserModel(
    @Expose var uId: Long, @Expose var bio: String?, @Expose var image: String, @Expose var name: String,
    @Expose var email: String, @Expose var token: String?, @Expose var address: String? = "", @Expose var itemCount: Integer,
    @Expose var itemScore: Integer, @Expose var balanceCoin: Integer, @Expose var publicKey: String, @Expose var createTimestamp: Long,
    @Expose var updateTimestamp: Long, @Expose var exsBalance: Int
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(s: String): UserModel {
            return Gson().fromJson<UserModel>(s.trim(), UserModel::class.java)
        }

        fun fromJson(jsonObject: JSONStringer?): UserModel {
            return fromJson(jsonObject.toString())
        }

        fun fromJson(jsonObject: JsonObject?): UserModel {
            return fromJson(jsonObject.toString())
        }

        fun fromJson(objects: Object?): UserModel {
            return fromJson(objects.toString())
        }

        fun fromJsonList(s: String): List<UserModel> {
            val listType = object : TypeToken<List<UserModel>>() {}.type
            return Gson().fromJson<List<UserModel>>(s, listType)
        }

        fun fromJsonList(objects: Object?): List<UserModel> {
            return fromJsonList(objects.toString())
        }

        fun fromJsonList(jsonObject: JSONStringer?): List<UserModel> {
            return fromJsonList(jsonObject.toString())
        }

    }
}