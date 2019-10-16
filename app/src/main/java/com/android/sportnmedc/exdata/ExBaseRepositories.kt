package com.android.sportnmedc.exdata

import androidx.lifecycle.MutableLiveData

interface ExBaseRepositories<T>{

    fun getDataList(): MutableLiveData<List<T>>
    fun getDataIndex(index:Int): MutableLiveData<T>


}