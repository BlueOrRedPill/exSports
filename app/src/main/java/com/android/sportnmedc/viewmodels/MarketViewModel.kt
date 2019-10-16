package com.android.sportnmedc.viewmodels

import androidx.lifecycle.LiveData
import com.android.sportnmedc.network.model.CardDataModel
import com.android.sportnmedc.repositories.CardRepository

class MarketViewModel: BaseViewModel(){

    /************************************/
    /*****  UI Data Responses Scope *****/
    private val mRepository = CardRepository<List<CardDataModel>>()

    fun marketList():LiveData<List<CardDataModel>> {
        return mRepository.getValueLiveData() as LiveData<List<CardDataModel>>
    }

    fun loadingIndicator():LiveData<Boolean>{
        return mRepository.getIndicatorLiveData()
    }

    fun error(): LiveData<String> {
        return mRepository.getErrorLiveData()
    }
    /*****  END UI Data Responses Scope *****/
    /****************************************/


    /******************************/
    /***** View Actions Scope *****/
    fun marketListAction(){
//        sportsType: String, resultCount: Int, order: String, lastMarketId: Long
        mRepository.marketSalesList("type", 20, "asc", 0)
    }
    /***** END View Actions Scope *****/
    /*********************************/
}