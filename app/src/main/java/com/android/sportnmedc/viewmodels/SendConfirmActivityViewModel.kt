package com.android.sportnmedc.viewmodels

import androidx.lifecycle.LiveData
import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.network.model.CardModel
import com.android.sportnmedc.network.model.PaymentModel
import com.android.sportnmedc.network.model.UserModel
import com.android.sportnmedc.repositories.BlockchainRepository
import com.android.sportnmedc.repositories.UserRepository
import com.google.gson.JsonObject

class SendConfirmActivityViewModel : BaseViewModel(){

    /************************************/
    /*****  UI Data Responses Scope *****/

    private val mRepository = BlockchainRepository<PaymentModel>()

    fun payment():LiveData<PaymentModel> {
        return mRepository.getValueLiveData()
    }

//    fun balanceOf():LiveData<PaymentModel> {
//        return mRepository.getValueLiveData()
//    }

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
    fun sendCoin(toPublicKey:String, amount: Int){
        mRepository.sendCoin(toPublicKey, amount)
    }
    /***** END View Actions Scope *****/
    /**********************************/


}