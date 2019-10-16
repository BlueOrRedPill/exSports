package com.android.sportnmedc.exdata

import androidx.lifecycle.MutableLiveData
import com.android.sportnmedc.exdata.user.ExUserModel
import com.android.sportnmedc.exdata.user.ExUserSource

class ExUserRepositoriesEx : ExBaseRepositories<ExUserModel> {

    val dataSource = ExUserSource()

    override fun getDataList(): MutableLiveData<List<ExUserModel>>{
        val  mExUserLiveData: MutableLiveData<List<ExUserModel>> = MutableLiveData()
        dataSource.load(object:BaseDataSource.OnLoad{
            override fun onLoaded() {
                mExUserLiveData.value = dataSource.getDataList()
            }

        })
        return  mExUserLiveData
    }

    override fun getDataIndex(index: Int):  MutableLiveData<ExUserModel>{
        val  mExUserLiveData: MutableLiveData<ExUserModel> = MutableLiveData()
        dataSource.getDataIndex(index)?.apply {
            mExUserLiveData.value = this
        }
        return   mExUserLiveData
    }



}