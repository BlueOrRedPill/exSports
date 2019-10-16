package com.android.sportnmedc.network.response

import com.android.sportnmedc.network.BaseData
import com.android.sportnmedc.network.NewBaseResponse
import com.android.sportnmedc.network.model.TransactionModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResLibraryItemTransactionList(
    @SerializedName("data")
    override val result: BaseData,
    @Expose override val warnings: List<String>,
    @Expose override val status: Integer,
    @Expose override val provider: String ) : NewBaseResponse<JsonObject> {
    override val data: JsonObject
        get() = result.data
//    override val data:  List<TransactionModel>
//        get() = TransactionModel.fromJsonList(result.data)

    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(s: String): ResLibraryItemTransactionList {
            return Gson().fromJson<ResLibraryItemTransactionList>(s, ResLibraryItemTransactionList::class.java)
        }
    }
}