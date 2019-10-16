package com.android.sportnmedc.network.response

import com.android.sportnmedc.network.BaseData
import com.android.sportnmedc.network.NewBaseResponse
import com.android.sportnmedc.network.model.CardDataModel
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class ResMarketRegister(
    @SerializedName("data")
    override val result: BaseData,
    override val warnings: List<String>,
    override val status: Integer,
    override val provider: String ) : NewBaseResponse<CardDataModel> {
    override val data: CardDataModel
        get() =  CardDataModel.fromJson(result.data)

    override fun toString(): String {
        return Gson().toJson(this)
    }
}