package com.android.sportnmedc.network.response

import com.android.sportnmedc.network.BaseData
import com.android.sportnmedc.network.NewBaseResponse
import com.android.sportnmedc.network.model.CardModel
import com.android.sportnmedc.network.model.CollectionTypeModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

/**
 * Library - card(All) list response data
 */
data class ResTokenCardDetail(
    @SerializedName("data")
    override val result: BaseData,
    override val warnings: List<String>,
    override val status: Integer,
    override val provider: String ) : NewBaseResponse<CardModel> {

    override val data: CardModel
        get() = CardModel.fromJson(result.data)

    override fun toString(): String {
        return Gson().toJson(this)
    }


}