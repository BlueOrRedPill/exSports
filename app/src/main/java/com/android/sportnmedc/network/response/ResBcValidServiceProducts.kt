package com.android.sportnmedc.network.response

import com.android.sportnmedc.network.BaseData
import com.android.sportnmedc.network.NewBaseResponse
import com.android.sportnmedc.network.model.CardModel
import com.android.sportnmedc.network.model.MarketModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ResBcValidServiceProducts(
    @SerializedName("data")
    override val result: BaseData,
    override val warnings: List<String>,
    override val status: Integer,
    override val provider: String ) : NewBaseResponse<List<MarketModel>> {
    override val data: List<MarketModel>
        get() = MarketModel.fromJsonList(result.data.getAsJsonArray("cardList").toString())

//    override val data: Object
//        get() = result.data as Object

    override fun toString(): String {
        return Gson().toJson(this)
    }
}