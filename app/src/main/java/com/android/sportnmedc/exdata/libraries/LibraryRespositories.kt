package com.android.sportnmedc.exdata.libraries

import com.android.sportnmedc.R
import com.android.sportnmedc.exdata.BaseDataSource
import java.util.*
import kotlin.collections.ArrayList

class LibraryRespositories:BaseDataSource<LibraryModel>{


    companion object {
        private var horseJumpingList = arrayListOf(R.drawable.ej01,
            R.drawable.ej02,
            R.drawable.ej03,
            R.drawable.ej04)
        private var horseRacingList = arrayListOf(R.drawable.e01,
            R.drawable.e02,
            R.drawable.e03,
            R.drawable.e04,
            R.drawable.e05,
            R.drawable.e06)
        private var boxingList = arrayListOf(R.drawable.b01,
            R.drawable.b02,
            R.drawable.b03,
            R.drawable.b04,
            R.drawable.b05,
            R.drawable.b06,
            R.drawable.b07,
            R.drawable.b08,
            R.drawable.b09,
            R.drawable.b10)
        private var muaythaiList = arrayListOf(R.drawable.m01,
            R.drawable.m02,
            R.drawable.m03,
            R.drawable.m04,
            R.drawable.m05,
            R.drawable.m06,
            R.drawable.m07,
            R.drawable.m08,
            R.drawable.m09)

        private var jiList = arrayListOf(R.drawable.j01,
            R.drawable.j02,
            R.drawable.j03,
            R.drawable.j04,
            R.drawable.j05,
            R.drawable.j06,
            R.drawable.j07,
            R.drawable.j08,
            R.drawable.j09,
            R.drawable.j10)
        private var volleyList = arrayListOf(R.drawable.v01,
            R.drawable.v02 ,
            R.drawable.v03,
            R.drawable.v04,
            R.drawable.v05,
            R.drawable.v06,
            R.drawable.v07,
            R.drawable.v08,
            R.drawable.v09,
            R.drawable.v10)

        fun getLibraryItems(name:String):ArrayList<Int>{
            if (name.equals("Jiu Jitsu",true)){
                return jiList
            }else if (name.equals("Volleyball",true)){
                return volleyList
            }else if (name.equals("Boxing",true)){
                return boxingList
            }else if (name.equals("Horse Racing",true)){
                return horseRacingList
            }else{
                return muaythaiList
            }

        }

        fun getOnSale():ArrayList<Int>{
            val list = ArrayList<Int>()
            list.addAll(jiList.slice(0..2))
            list.addAll(volleyList.slice(0..2))
            list.addAll(muaythaiList.slice(0..2))
            list.shuffle()
            return list
        }

    }
    private var mList = ArrayList<LibraryModel>()

    override fun getDataList(): List<LibraryModel> {
        return  mList
    }

    override fun getDataIndex(index: Int): LibraryModel? {
        return mList.get(index)
    }

    override fun load(onLoaded: BaseDataSource.OnLoad) {
        if (mList.isEmpty()){
            mList.add(LibraryModel("Muaythai", R.drawable.ex_library_muaythai, Random().nextInt(24),24))
            mList.add(LibraryModel("Volleyball", R.drawable.ex_library_volleyball, Random().nextInt(24),24))
            mList.add(LibraryModel("Jiu Jitsu", R.drawable.ex_library_udo, Random().nextInt(24),24))
            mList.add(LibraryModel("Boxing", R.drawable.ex_library_boxing, Random().nextInt(24),24))
            mList.add(LibraryModel("Horse Racing", R.drawable.ex_library_horse, Random().nextInt(24),24))
        }
        onLoaded.onLoaded()
    }

}