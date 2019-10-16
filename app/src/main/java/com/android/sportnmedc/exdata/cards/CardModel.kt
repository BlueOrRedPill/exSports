package com.android.sportnmedc.exdata.cards

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CardModel(var cardId:String,var name:String,var image:Int,var skill:String,var sport:String,var event:String,var country:String): Parcelable