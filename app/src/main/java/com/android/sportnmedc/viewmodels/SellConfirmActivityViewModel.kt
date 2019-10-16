package com.android.sportnmedc.viewmodels

import androidx.lifecycle.LiveData
import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.network.model.CardModel
import com.android.sportnmedc.network.model.PaymentModel
import com.android.sportnmedc.network.model.UserModel
import com.android.sportnmedc.repositories.BlockchainRepository
import com.android.sportnmedc.repositories.UserRepository
import com.google.gson.JsonObject

class SellConfirmActivityViewModel : BaseViewModel(){

    /************************************/
    /*****  UI Data Responses Scope *****/

    private val mRepository = BlockchainRepository<String>()

    fun purchase():LiveData<String> {
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
    fun purchaseServiceProduct(productId: Int, quantity: Int){
        mRepository.purchaseServiceProduct(productId, quantity)
    }

//    fun sell(productId: Int, quantity: Int){
//        mRepository.purchaseServiceProduct(productId, quantity)
//    }

    /***** END View Actions Scope *****/
    /**********************************/


}