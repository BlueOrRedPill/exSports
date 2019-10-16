package com.android.sportnmedc.exdata.libraries

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class LibraryModel(var name:String,var imageCover:Int,var currentCount:Int,var maxCount:Int):Parcelable