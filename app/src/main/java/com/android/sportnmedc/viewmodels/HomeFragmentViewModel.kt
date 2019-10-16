package com.android.sportnmedc.viewmodels

import androidx.lifecycle.LiveData
import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.network.model.CardModel
import com.android.sportnmedc.network.model.MarketModel
import com.android.sportnmedc.network.model.UserModel
import com.android.sportnmedc.repositories.BlockchainRepository
import com.android.sportnmedc.repositories.UserRepository
import com.google.gson.JsonObject

class HomeFragmentViewModel : BaseViewModel(){

    /************************************/
    /*****  UI Data Responses Scope *****/

    private val mRepository = UserRepository()
    private val mBcRepository = BlockchainRepository<List<MarketModel>>()

    fun user():LiveData<UserModel> {
        return mRepository.getValueLiveData()
    }

    fun serviceProducts():LiveData<List<MarketModel>> {
        return mBcRepository.getValueLiveData()
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
    fun bcValidServiceProducts() {
        mBcRepository.bcValidServiceProducts()
    }


    /***** END View Actions Scope *****/
    /**********************************/


}