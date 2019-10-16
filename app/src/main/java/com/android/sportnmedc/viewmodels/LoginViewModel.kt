package com.android.sportnmedc.viewmodels

import androidx.lifecycle.LiveData
import com.android.sportnmedc.network.model.UserModel
import com.android.sportnmedc.repositories.UserRepository
import java.io.File

class LoginViewModel: BaseViewModel(){

    /************************************/
    /*****  UI Data Responses Scope *****/
    private val mRepository = UserRepository()

    fun user():LiveData<UserModel> {
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
    fun loginAction(email:String?,password:String?){
        when {
            email.isNullOrEmpty() -> mRepository.setLocalErrorMessage("Email is invalid.")
            password.isNullOrEmpty() -> mRepository.setLocalErrorMessage("Password is invalid.")
            else -> mRepository.login(email,password)
        }
    }

    fun userProfile(targetUid:Long) {
        mRepository.userProfile(targetUid)
    }

    fun registerAction(email:String, name:String, userBio: String, password:String, confirmPassword:String, secondPassword: String, file: File?) {
        when {
            email.isNullOrEmpty() -> mRepository.setLocalErrorMessage("Email is invalid.")
            password.isNullOrEmpty() -> mRepository.setLocalErrorMessage("Password is invalid.")
            confirmPassword.isNullOrEmpty() -> mRepository.setLocalErrorMessage("Confirm Password is invalid.")
            name.isNullOrEmpty() -> mRepository.setLocalErrorMessage("Name is invalid.")
            secondPassword.isNullOrEmpty() -> mRepository.setLocalErrorMessage("Second Password is invalid.")
            password != confirmPassword -> mRepository.setLocalErrorMessage("Confirm Password is not the same.")
            else -> mRepository.singUp(email, name, userBio, password, secondPassword, file)
        }
    }
    /***** END View Actions Scope *****/
    /*********************************/
}