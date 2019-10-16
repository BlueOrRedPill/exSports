package com.android.sportnmedc.exdata

interface BaseDataSource<T>{

    fun getDataList():List<T>
    fun getDataIndex(index:Int):T?

    fun load(onLoaded: OnLoad)


    fun loadFromNetwork(){

    }

    fun loadFromDB(){

    }

    interface OnLoad{
        fun onLoaded()
    }


}