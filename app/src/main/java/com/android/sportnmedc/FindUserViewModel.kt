package com.android.sportnmedc

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.sportnmedc.exdata.ExUserRepositoriesEx
import com.android.sportnmedc.exdata.user.ExUserModel

class FindUserViewModel :ViewModel(){

   private val mUserRepositories = ExUserRepositoriesEx()

    fun getUser(): LiveData<List<ExUserModel>> {
        return mUserRepositories.getDataList()
    }
}