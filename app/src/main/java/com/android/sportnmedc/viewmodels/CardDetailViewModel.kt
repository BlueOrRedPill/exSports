package com.android.sportnmedc.viewmodels

import androidx.lifecycle.LiveData
import com.android.sportnmedc.network.model.CardModel
import com.android.sportnmedc.repositories.CardRepository

class CardDetailViewModel: BaseViewModel(){

    /************************************/
    /*****  UI Data Responses Scope *****/
    private val mRepository = CardRepository<CardModel>()

    fun card():LiveData<CardModel> {
        return mRepository.getValueLiveData()
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

    fun cardDetail(tokenId: Int?) {
        mRepository.cardDetail(tokenId)
    }
    /***** END View Actions Scope *****/
    /*********************************/
}