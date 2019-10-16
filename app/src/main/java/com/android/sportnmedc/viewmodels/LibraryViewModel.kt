package com.android.sportnmedc.viewmodels

import androidx.lifecycle.LiveData
import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.network.model.CollectionTypeModel
import com.android.sportnmedc.network.model.UserModel
import com.android.sportnmedc.repositories.CollectionRepository
import com.android.sportnmedc.repositories.UserRepository
import com.google.gson.JsonObject
import java.io.File

class LibraryViewModel: BaseViewModel(){

    /************************************/
    /*****  UI Data Responses Scope *****/
    private val mRepository = CollectionRepository<List<CollectionTypeModel>>()

    fun sportsList():LiveData<List<CollectionTypeModel>> {
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
    fun getSportsList(){
        mRepository.sportsList()
    }

//    fun loginAction(email:String?,password:String?){
//        when {
//            email.isNullOrEmpty() -> mRepository.setLocalErrorMessage("Email is invalid.")
//            password.isNullOrEmpty() -> mRepository.setLocalErrorMessage("Password is invalid.")
//            else -> mRepository.login(email,password)
//        }
//    }
    /***** END View Actions Scope *****/
    /*********************************/
}