package com.android.sportnmedc.network.response

import com.android.sportnmedc.network.BaseData
import com.android.sportnmedc.network.NewBaseResponse
import com.android.sportnmedc.network.model.TradeModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

/**
 * Library - sale card list Response
 */
data class ResTradeList(
    @SerializedName("data")
    override val result: BaseData,
    override val warnings: List<String>,
    override val status: Integer,
    override val provider: String ) : NewBaseResponse<JsonObject> {
    override val data: JsonObject
        get() = result.data
//    override val data: List<TradeModel>
//        get() = TradeModel.fromJsonList(result.data)

    override fun toString(): String {
        return Gson().toJson(this)
    }
}