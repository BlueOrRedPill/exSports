package com.android.sportnmedc.viewmodels

import androidx.lifecycle.LiveData
import com.android.sportnmedc.network.model.CardDataModel
import com.android.sportnmedc.network.model.CardModel
import com.android.sportnmedc.repositories.BlockchainRepository
import com.android.sportnmedc.repositories.CardRepository
import com.google.gson.JsonObject

class LibraryDetailViewModel: BaseViewModel(){

    /************************************/
    /*****  UI Data Responses Scope *****/
    private val mRepository = CardRepository<List<CardModel>>()
    private val mTokensRepository = BlockchainRepository<List<CardModel>>()

    fun cardList():LiveData<List<CardModel>> {
        return mRepository.getValueLiveData()
    }

    fun tokensOwned():LiveData<List<CardModel>> {
        return mTokensRepository.getValueLiveData()
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
    fun getCardList(sportsId: String, count: Int, sortOrder: Int, lastId: Long){
        mRepository.cardList(sportsId, count, sortOrder, lastId)
    }

    fun getTokensOwned() {
        mTokensRepository.bcTokensOwned()
    }
    /***** END View Actions Scope *****/
    /*********************************/
}