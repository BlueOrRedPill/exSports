package com.android.sportnmedc.network.response

import com.android.sportnmedc.network.BaseData
import com.android.sportnmedc.network.NewBaseResponse
import com.android.sportnmedc.network.model.CollectionTypeModel
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class ResSportsList(
    @SerializedName("data")
    override val result: BaseData,
    override val warnings: List<String>,
    override val status: Integer,
    override val provider: String ) : NewBaseResponse<List<CollectionTypeModel>?> {

    override val data: List<CollectionTypeModel>?
        get() = result.data?.let { CollectionTypeModel.fromJsonList(it.getAsJsonArray("sportsList")) }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}