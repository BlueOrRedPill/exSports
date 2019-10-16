package com.android.sportnmedc.network.model

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import org.json.JSONStringer
import java.util.*

@Parcelize
data class CollectionTypeModel(@Expose val sportsId: String, @Expose val sportsName: String, @Expose val itemCount: Int, @Expose val itemMaxCount: Int, @Expose val imageCover:Int):
    Parcelable {
    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(s: String): CollectionTypeModel {
            return Gson().fromJson<CollectionTypeModel>(s, CollectionTypeModel::class.java)
        }

        fun fromJson(jsonObject: JsonObject): CollectionTypeModel {
            return fromJson(jsonObject.toString())
        }

        fun fromJson(objects: Object): CollectionTypeModel {
            return fromJson(objects.toString())
        }

        fun fromJson(objects: JSONStringer): CollectionTypeModel {
            return fromJson(objects.toString())
        }

        fun fromJsonList(s: String): List<CollectionTypeModel>? {
            val listType = object : TypeToken<List<CollectionTypeModel>>() {}.type
            return Gson().fromJson<List<CollectionTypeModel>>(s, listType)
        }

        fun fromJsonList(objects: Object): List<CollectionTypeModel>? {
            return fromJsonList(objects.toString())
        }

        fun fromJsonList(jsonObject: JsonArray?): List<CollectionTypeModel>? {
            return fromJsonList(jsonObject.toString())
        }

        fun fromJsonList(jsonObject: JSONStringer?): List<CollectionTypeModel>? {
            return fromJsonList(jsonObject.toString())
        }
    }
}