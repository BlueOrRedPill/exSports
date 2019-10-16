package com.android.sportnmedc.exdata.trades

import android.os.Parcelable
import com.android.sportnmedc.exdata.user.ExUserModel
import kotlinx.android.parcel.Parcelize


@Parcelize
data class TradeModel(var cardIn:ArrayList<Int>,var cardOut:ArrayList<Int>,var exUser:ExUserModel):Parcelable