package com.android.sportnmedc.network.response

import com.android.sportnmedc.network.BaseData
import com.android.sportnmedc.network.NewBaseResponse
import com.android.sportnmedc.network.model.UserModel
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Auth - login response data
 */
data class ResUserUpdatePassword(
    @SerializedName("data")
    override val result: BaseData,
    override val warnings: List<String>,
    override val status: Integer,
    override val provider: String ) : NewBaseResponse<UserModel> {
    override val data: UserModel
        get() = UserModel.fromJson(result.data)

    override fun toString(): String {
        return Gson().toJson(this)
    }
}