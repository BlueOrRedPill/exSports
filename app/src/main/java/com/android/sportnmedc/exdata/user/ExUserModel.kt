package com.android.sportnmedc.exdata.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class ExUserModel(var name:String, var imgProfile:Int, var score:Int, var items:Int, var cardQuality:Int):Parcelable