package com.android.sportnmedc.exdata.markets

import com.android.sportnmedc.R
import com.android.sportnmedc.exdata.BaseDataSource
import java.util.*

class MarketRepositories:BaseDataSource<Market>{

    private var mList:ArrayList<Market> =ArrayList()

    override fun getDataList(): List<Market> {
      return mList
    }

    override fun getDataIndex(index:Int): Market? {
      return mList.get(index)
    }

    override fun load(onLoaded: BaseDataSource.OnLoad) {
        if (mList.isEmpty()){
            mList.add(Market(R.drawable.m01,"Tony",50))
            mList.add(Market(R.drawable.m02,"Gustavo",60))
            mList.add(Market(R.drawable.m03,"Leo",70))
            mList.add(Market(R.drawable.v01,"Salah",100))
            mList.add(Market(R.drawable.v02,"Muhammad",150))
            mList.add(Market(R.drawable.v03,"Enriko",200))
            mList.add(Market(R.drawable.j01,"Ronaldo",20))
            mList.add(Market(R.drawable.j02,"William",80))
            mList.add(Market(R.drawable.j03,"Davic",90))
            mList.shuffle()

        }
        onLoaded.onLoaded()
    }

     fun sort(isDescending:Boolean){
         if (isDescending){
             mList.sortByDescending {  market -> market.price  }
         }else{
             mList.sortBy { market -> market.price  }
         }
     }


}