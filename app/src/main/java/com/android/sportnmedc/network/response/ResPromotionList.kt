package com.android.sportnmedc.network.response

import com.android.sportnmedc.network.BaseData
import com.android.sportnmedc.network.BaseResponse
import com.android.sportnmedc.network.NewBaseResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONStringer

data class ResPromotionList(
    @SerializedName("data")
    override val result: BaseData,
    override val warnings: List<String>,
    override val status: Integer,
    override val provider: String
) : NewBaseResponse<JsonObject> {

    override fun toString(): String {
        return Gson().toJson(this)
    }

    override val data = result.data
}