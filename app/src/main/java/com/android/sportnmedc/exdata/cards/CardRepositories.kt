package com.android.sportnmedc.exdata.cards

import com.android.sportnmedc.R
import com.android.sportnmedc.exdata.BaseDataSource

class CardRepositories:BaseDataSource<CardModel>{

    private var mList = ArrayList<CardModel>()

    override fun getDataList(): ArrayList<CardModel> {
        return  mList
    }

    override fun getDataIndex(index: Int): CardModel? {
        return mList.get(index)
    }


    private var muaythaiList = arrayListOf(R.drawable.m01,
        R.drawable.m02,
        R.drawable.m03,
        R.drawable.m04,
        R.drawable.m05,
        R.drawable.m06,
        R.drawable.m07,
        R.drawable.m08,
        R.drawable.m09)

    override fun load(onLoaded: BaseDataSource.OnLoad) {
        if (mList.isEmpty()){
//            mList.add(CardModel("EJ01","Zinius", R.drawable.ej01,"HIGH","Horse Jumping","FEI","FR"))
//            mList.add(CardModel("EJ02","BULL RUN", R.drawable.ej02,"HIGH","Horse Jumping","FEI","FR"))
//            mList.add(CardModel("EJ03","Breitling LS", R.drawable.ej03,"HIGH","Horse Jumping","FEI","FR"))
//            mList.add(CardModel("EJ04","Mary Lou 194", R.drawable.ej04,"HIGH","Horse Jumping","FEI","FR"))
//
//            mList.add(CardModel("E01","Winx", R.drawable.e01,"HIGH","Horse Racing","FEI","FR"))
//            mList.add(CardModel("E02","The Autumn Sun", R.drawable.e02,"HIGH","Horse Racing","FEI","AUS"))
//            mList.add(CardModel("E03","Sunlight", R.drawable.e03,"HIGH","Horse Racing","FEI","UAE"))
//            mList.add(CardModel("E04","Redzel", R.drawable.e04,"HIGH","Horse Racing","FEI","UAE"))
//            mList.add(CardModel("E05","Cross Counter", R.drawable.e05,"HIGH","Horse Racing","FEI","UAE"))
//            mList.add(CardModel("E06","Arcadia Queen", R.drawable.e06,"HIGH","Horse Racing","FEI","UAE"))

            mList.add(CardModel("B01","Many Pacquiao", R.drawable.b01,"HIGH","BOXING","IBF","PHI"))
            mList.add(CardModel("B02","Floyd Mayweather JR", R.drawable.b02,"HIGH","BOXING","IBF","USA"))
            mList.add(CardModel("B03","Isley troy", R.drawable.b03,"HIGH","BOXING","AIBA","USA"))
            mList.add(CardModel("B04","Whittaker Benjamin", R.drawable.b04,"HIGH","BOXING","AIBA","ENG"))
            mList.add(CardModel("B05","Apetz Nadine", R.drawable.b05,"HIGH","BOXING","AIBA","GER"))
            mList.add(CardModel("B06","Silva Rondeau Myriam", R.drawable.b06,"HIGH","BOXING","AIBA","CAN"))
            mList.add(CardModel("B07","Thibeault Tammara", R.drawable.b07,"HIGH","BOXING","AIBA","CAN"))
            mList.add(CardModel("B08","Graham Naomi", R.drawable.b08,"HIGH","BOXING","AIBA","USA"))
            mList.add(CardModel("B09","Gale Natasha", R.drawable.b09,"HIGH","BOXING","AIBA","ENG"))
            mList.add(CardModel("B10","Baraou Abass", R.drawable.b10,"HIGH","BOXING","AIBA","GER"))

            mList.add(CardModel("MT01","Changpuek", R.drawable.m01,"HIGH","MUAYTHAI","EFN","THA"))
            mList.add(CardModel("MT02","Buakaw Banchamek", R.drawable.m02,"HIGH","MUAYTHAI","EFN","THA"))
            mList.add(CardModel("MT03","Bard Hari", R.drawable.m03,"HIGH","MUAYTHAI","EFN","THA"))
            mList.add(CardModel("MT04","Alex Perera", R.drawable.m04,"HIGH","MUAYTHAI","GLORY","BRA"))
            mList.add(CardModel("MT05","Andre kulebin", R.drawable.m05,"HIGH","MUAYTHAI","EFN","BLR"))
            mList.shuffle()
        }
        onLoaded.onLoaded()
    }

}